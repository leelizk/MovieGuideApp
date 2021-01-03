package com.example.movieguideapp.base.common.deeplink

import androidx.annotation.VisibleForTesting

object UriArgumentsParser {

    private val SEGMENTS_DELIMITERS = arrayOf("://", "/", "?", "&")
    private const val ARGUMENT_START = "{"
    private const val ARGUMENT_END = "}"
    private const val PARAMETER_DELIMITER = "="
    private const val EMPTY = ""

    /**
     * Split [uri] into segments by delimiters.
     * @return list of segments
     */
    fun splitToSegments(uri: String): List<String> =
        uri.split(*SEGMENTS_DELIMITERS).filter { it.isNotBlank() }

    /**
     * Parse [uri] based on [template] and return map of arguments if [uri] matches [template].
     * Template can have arguments {arg_name} for path segments and query parameters, e.g.
     * http://site.com/{argument_segment1}/segment2?param1=val1&param2={argument_param2_value}
     * @param uri uri to parse
     * @param template template for uri parsing,
     * @return map of arguments if [uri] matches [template] else null
     */
    fun parse(uri: String, template: String): Map<String, String>? {
        val uriSegments = splitToSegments(uri)
        val templateSegments = splitToSegments(template)
        return buildArguments(uriSegments, templateSegments)
    }

    /**
     * Build arguments key-value for [uriSegments] based on [templateSegments]. Return map if segments match else null.
     * @param uriSegments uri segments to parse
     * @param templateSegments template segments to parse
     */
    @VisibleForTesting
    fun buildArguments(
        uriSegments: List<String>,
        templateSegments: List<String>
    ): Map<String, String>? {
        val arguments = hashMapOf<String, String>()

        // build path arguments
        val templatePaths = getPaths(templateSegments)
        val uriPaths = getPaths(uriSegments)

        if (templatePaths.size != uriPaths.size) {
            return null
        }

        templatePaths.forEachIndexed { i, templatePath ->
            val uriPath = uriPaths[i]
            if (uriPath != templatePath) {
                if (isArgument(templatePath)) {
                    arguments[getArgumentName(templatePath)] = uriPath
                } else {
                    return null
                }
            }
        }

        // build parameter arguments
        val templateParams = getParameters(templateSegments)
        val uriParameters = getParameters(uriSegments)

        uriParameters.forEach { (uriParameterKey, uriParameterValue) ->
            arguments[uriParameterKey] = uriParameterValue
        }

        templateParams.forEach { (templateParameterKey, templateParameterValue) ->
            val uriParameterValue = uriParameters[templateParameterKey] ?: return null

            if (isArgument(templateParameterValue)) {
                arguments[getArgumentName(templateParameterValue)] = uriParameterValue
            } else if (uriParameterValue != templateParameterValue) {
                return null
            }
        }

        return arguments
    }

    /**
     * Parse [segment] to match query parameter format (e.g. param1=5 or param2={param})
     * and return parameter key-value pair.
     * @param segment segment to parse according to query parameter format
     * @return parameter key-value pair if matches format else null
     */
    @VisibleForTesting
    fun getParameterPair(segment: String): Pair<String, String>? {
        val parts = segment.split(PARAMETER_DELIMITER)
        return if (parts.size == 2) {
            parts[0] to parts[1]
        } else {
            null
        }
    }

    /**
     * Build parameters key-value map form all parameter segment is [segments].
     * @param segments segments to parse
     * @return parameters key-value map
     */
    fun getParameters(segments: List<String>): Map<String, String> {
        val parameters = hashMapOf<String, String>()

        for (segment in segments) {
            getParameterPair(segment)?.let { (key, value) ->
                parameters[key] = value
            }
        }

        return parameters
    }

    /**
     * Filter [segments] and return only paths.
     * @param segments segments to filter
     * @return list of path segments
     */
    @VisibleForTesting
    fun getPaths(segments: List<String>): List<String> {
        return segments.filter { getParameterPair(it) == null }
    }

    /**
     * Check if [segment] is argument (with brackets), e.g. {arg1} or not
     * @param segment parsed segment
     * @return true if segment is argument
     */
    @VisibleForTesting
    fun isArgument(segment: String): Boolean {
        return segment.startsWith(ARGUMENT_START) && segment.endsWith(ARGUMENT_END)
    }

    /**
     * Remove brackets from argument [segment] to get argument name, e.g. {arg1} -> arg1
     *
     * @param segment parsed argument segment with brackets
     * @return argument name without brackets
     */
    @VisibleForTesting
    fun getArgumentName(segment: String): String {
        return segment.replace(ARGUMENT_START, EMPTY).replace(ARGUMENT_END, EMPTY)
    }
}
