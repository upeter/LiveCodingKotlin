package org.a_intro

import org.apache.commons.io.FileUtils.copyURLToFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalDateTime

data class PhotoFinal(val url: URL, val createdAt: LocalDateTime = LocalDateTime.now(), val ratings: RatingsFinal = emptyList()) : Comparable<PhotoFinal> {

    constructor(path: String, createdAt: LocalDateTime = LocalDateTime.now(), ratings: List<Int> = emptyList()) : this(URL(path), createdAt, ratings)


    fun maxAndMinRate(): Pair<Int, Int>? {
//        return if (ratings.any()) {
//            Pair(Collections.max(ratings), Collections.min(ratings))
//        } else null
        val (max, min) = ratings.max() to ratings.min()
        return if (max != null && min != null) max to min else null
    }

    override fun compareTo(other: PhotoFinal): Int =
            this.url.toString().compareTo(other.url.toString())

}

data class AlbumFinal(val photos: List<PhotoFinal>, private val logger: Logger = LoggerFactory.getLogger(PhotoFinal::class.java)) : Logger by logger {
    operator fun plus(photo: PhotoFinal): AlbumFinal = copy(photos = photos + photo)

    infix fun union(other:AlbumFinal) = copy(photos = (this.photos union other.photos).toList())

    fun averageRating() = photos.flatMap { it.ratings }.average()

    val avgRating by lazy {
        photos.flatMap { it.ratings }.average()
    }

    fun filter(predicate: (PhotoFinal) -> Boolean) = photos.filter(predicate)

    fun forEach(photoFun: (PhotoFinal) -> Unit) = photos.forEach(photoFun)

    fun copyAllTo(file: File) {
        require(file.isDirectory, { "${file.absoluteFile} is not a directory" })
        photos.forEach {
            try {
                it.copyTo(file)
                info("Succesfully copied ${it.url}")
            } catch (ex: IOException) {
                error("Failed to copy ${it.url}", ex)
            }
        }
    }
}


//Extensions
@Throws(MalformedURLException::class, IOException::class)
fun URL.copyTo(target: File): File {
    val to = if (target.isDirectory) {
        File(target, this.file.split("/").last())
    } else target
    copyURLToFile(this, to)
    return to
}

//type alias
typealias RatingsFinal = List<Int>

fun RatingsFinal.maxAndMinRating(): Pair<Int, Int>? {
//        return if (ratings.any()) {
//            Pair(Collections.max(ratings), Collections.min(ratings))
//        } else null
    val (min, max) = this.min() to this.max()
    return if (max != null && min != null) min to max else null
}

fun PhotoFinal.copyTo(target: File) = this.url.copyTo(target)

//main
fun main(args: Array<String>) {
    val ratings = (2..10 step 2).toList()
    val photo1 = PhotoFinal("https://twitter.com/kotlin", ratings = ratings)
    val photo2 = PhotoFinal("https://savvyapps.com/blog/kotlin-tips-android-development")
    val photo3 = PhotoFinal("https://savvyapps.com/blog/kotlin-tips-android-development")

    val (min, max) = photo1.ratings.maxAndMinRating() ?: Pair(-1, -1)

    println("Min Rating: ${min} Max Rating: ${max}")







    (photo1.ratings union photo2.ratings).average()
    val album = AlbumFinal(listOf(photo1)) + photo2 + photo1
    album.filter { it.createdAt > LocalDateTime.now().minusDays(7) }

    val combined = AlbumFinal(listOf(photo3)) union album

}


/* Programma
- Photo
-- class with constructor
-- default arguments
-- auxiliary constructor (URL)
-- interface Comparable
-- minMaxRating:
--- Pair
--- Nullable types (show usage too) TODO
-- data class
-- Extensions
--- copyTo
-- Delegation logging
- AlbumFinal:
-- average rating
-- ranges TODO
-- plus operator
-- immutability (copy)
-- Function declaration (filter)
-- TODO's
--- when
--- extractors
 */




