package com.example.movieguideapp.base.common.deeplink

class DeepLinkProvider<T>(
    private vararg val templates: String,
    private val buildDeepLink: DeepLinkBuilder.(args: Map<String, String>) -> DeepLink<T>
) {

    fun match(deepLinkBuilder: DeepLinkBuilder, uri: String): DeepLink<T>? {
        templates.forEach { template ->
            UriArgumentsParser.parse(uri, template)?.let { arguments ->
                return buildDeepLink(deepLinkBuilder, arguments)
            }
        }

        return null
    }
}
