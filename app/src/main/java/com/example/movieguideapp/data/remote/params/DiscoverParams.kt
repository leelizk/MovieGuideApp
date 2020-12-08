package com.example.movieguideapp.data.remote.params

/**
@ param page                 Minimum value is 1
 * @param language             ISO 639-1 code.
 * @param sortBy               Available options are vote_average.desc, vote_average.asc, release_date.desc,
 *                             release_date.asc, popularity.desc, popularity.asc
 * @param includeAdult         Toggle the inclusion of adult titles
 * @param year                 Filter the results release dates to matches that include this value
 * @param primaryReleaseYear   Filter the results so that only the primary release date year has this value
 * @param voteCountGte         Only include movies that are equal to, or have a vote count higher than this value
 * @param voteAverageGte       Only include movies that are equal to, or have a higher average rating than this
 *                             value
 * @param withGenres           Only include movies with the specified genres. Expected value is an integer (the id
 *                             of a genre). Multiple values can be specified. Comma separated indicates an 'AND'
 *                             query, while a pipe (|) separated value indicates an 'OR'.
 * @param releaseDateGte       The minimum release to include. Expected format is YYYY-MM-DD
 * @param releaseDateLte       The maximum release to include. Expected format is YYYY-MM-DD
 * @param certificationCountry Only include movies with certifications for a specific country. When this value is
 *                             specified, 'certificationLte' is required. A ISO 3166-1 is expected.
 * @param certificationLte     Only include movies with this certification and lower. Expected value is a valid
 *                             certification for the specified 'certificationCountry'.
 * @param withCompanies        Filter movies to include a specific company. Expected value is an integer (the id of
 *                             a company). They can be comma separated to indicate an 'AND' query.
 **/

class DiscoverParams(
private val page:Int?=1,
private val language:String?="en"
)