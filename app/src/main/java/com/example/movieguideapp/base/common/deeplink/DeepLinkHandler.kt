package com.example.movieguideapp.base.common.deeplink

import androidx.annotation.VisibleForTesting
import com.example.movieguideapp.base.utils.UriWrapper
import com.example.movieguideapp.base.utils.toMap
import org.json.JSONObject

class DeepLinkHandler(
	private val deepLinkBuilder: DeepLinkBuilder,
	private val uriWrapper: UriWrapper
) {

	companion object {
		private const val DEEP_LINK_PREFIX = "freshlymobile://"
		private const val UNIVERSAL_LINK_PREFIX = "https://freshly.app.link"
		private const val UNIVERSAL_TEST_LINK_PREFIX = "https://freshly.test-app.link"
		private val SITE_LINK_PREFIXES = arrayOf(
			"https://freshly.com",
			"https://www.freshly.com"
		)

		private const val PARAM_MOBILE = "mobile"
		private const val PARAM_CONFIRMED = "confirmed"
		private val PARAM_IGNORED_PREFIXES = arrayOf("$", "~", "+")

		// Screen
		private const val TEMPLATE_DIET_PREFS = "diet-prefs"
		private const val TEMPLATE_RATINGS = "ratings"
		private const val TEMPLATE_REFER_FRIEND = "refer-a-friend"
		private const val TEMPLATE_NOTIFICATIONS = "notifications"
		private const val TEMPLATE_MANAGE_SUBSCRIPTION = "manage-subscriptions"
		private const val TEMPLATE_PAUSE_DELIVERIES = "pause-deliveries"
		private const val TEMPLATE_WEEKLY_DEFAULT_PLAN = "weekly-default-plan"
		private const val TEMPLATE_DELIVERY_FREQUENCY = "delivery-frequency"
		private const val TEMPLATE_CHANGE_MEALS = "change-meals"

		// Action
		private const val TEMPLATE_SKIP_FIRST_OPEN_ORDER = "skip-first-week"
		private const val TEMPLATE_CHANGE_MEALS_FIRST_OPEN_ORDER = "change-meals-first-week"

		fun canBeRedirectedToWeb(uri: String): Boolean {
			return SITE_LINK_PREFIXES.any { uri.startsWith(it) } ||
					uri.startsWith(UNIVERSAL_LINK_PREFIX) ||
					uri.startsWith(UNIVERSAL_TEST_LINK_PREFIX)
		}
	}

	// Screen deep link templates
	val dietaryPreferencesDeepLinkTemplate = deepLink(TEMPLATE_DIET_PREFS)
	val ratingsDeepLinkTemplate = deepLink(TEMPLATE_RATINGS)
	val referFriendDeepLinkTemplate = deepLink(TEMPLATE_REFER_FRIEND)
	val notificationsDeepLinkTemplate = deepLink(TEMPLATE_NOTIFICATIONS)
	val manageSubscriptionDeepLinkTemplate = deepLink(TEMPLATE_MANAGE_SUBSCRIPTION)
	val changeMealsDeepLinkTemplate = deepLink(TEMPLATE_CHANGE_MEALS)

	// Action deep link templates
	val skipFirstOpenOrderConfirmedActionDeepLinkTemplate = actionDeepLink(
		"$TEMPLATE_SKIP_FIRST_OPEN_ORDER?$PARAM_CONFIRMED=true"
	)

	val changeMealsFirstOpenOrderConfirmedActionDeepLinkTemplate = actionDeepLink(
		"$TEMPLATE_CHANGE_MEALS_FIRST_OPEN_ORDER?$PARAM_CONFIRMED=true"
	)


	@VisibleForTesting
	fun bothLinks(template: String): Array<String> = arrayOf(
		deepLink(template),
		universalLink(template),
		universalTestLink(template)
	)

	@VisibleForTesting
	fun deepLink(template: String, screen: Boolean = true): String =
		"${DEEP_LINK_PREFIX}${if (screen) "screen" else "action"}/$template"

	private fun actionDeepLink(template: String) = deepLink(template, screen = false)

	@VisibleForTesting
	fun universalLink(template: String): String = "$UNIVERSAL_LINK_PREFIX?mobile=$template"

	private fun universalTestLink(template: String): String =
		"$UNIVERSAL_TEST_LINK_PREFIX?mobile=$template"

	@VisibleForTesting
	fun getUriFromParams(params: Map<String, String>): String? {
		val mobile = params[PARAM_MOBILE] ?: return null

		val joinedParams = params.asSequence()
			.filter { (key, value) ->
				key != PARAM_MOBILE && value.isNotBlank() && PARAM_IGNORED_PREFIXES.none {
					key.startsWith(
						it
					)
				}
			}
			.map { (key, value) -> "&$key=$value" }
			.joinToString("")

		return universalLink("$mobile$joinedParams")
	}

	@VisibleForTesting
	fun getReferringUri(referringParams: JSONObject?): String? =
		referringParams?.let { getUriFromParams(it.toMap()) }

	@VisibleForTesting
	fun getSiteUri(intentUri: String?): String? {
		if (intentUri == null) return null
		if (SITE_LINK_PREFIXES.none { intentUri.startsWith(it) }) return null

		val params = UriArgumentsParser.getParameters(UriArgumentsParser.splitToSegments(intentUri))
		return getUriFromParams(params)
	}

	@VisibleForTesting
	fun getUriWithSchema(uri: String?): String? =
		if (uri != null && uriWrapper.parse(uri).scheme.isNullOrBlank()) {
			"$DEEP_LINK_PREFIX$uri"
		} else {
			uri
		}

	@VisibleForTesting
	fun getUri(intentUri: String?, referringParams: JSONObject?): String? =
		getReferringUri(referringParams)
			?: getSiteUri(intentUri)
			?: getUriWithSchema(intentUri)


}
