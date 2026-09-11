package com.example.cst438project1.data.api

import com.example.cst438project1.BuildConfig
import java.security.MessageDigest

object SignatureUtils {

    /**
     * Section 8: Signing Calls
     * 1. Sort params alphabetically
     * 2. Concatenate nameValue
     * 3. Append secret
     * 4. MD5 hash
     */
    fun generateSignature(params: Map<String, String>): String {
        val sortedString = params.entries
            .filter { it.key != "format" && it.key != "callback" }
            .sortedBy { it.key }
            .joinToString("") { "${it.key}${it.value}" }
        
        val stringToHash = sortedString + BuildConfig.LASTFM_API_SECRET
        return md5(stringToHash)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}
