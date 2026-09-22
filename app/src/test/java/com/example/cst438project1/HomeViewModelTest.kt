package com.example.cst438project1

import com.example.cst438project1.ui.viewmodel.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewModelTest {

    private val viewModel = HomeViewModel()

    @Test
    fun formatBio_includesFirstTwoSentences_whenUnder300Chars() {
        val sentence1 = "Coldplay are a British rock band formed in London in 1997."
        val sentence2 = "They consist of vocalist Chris Martin and guitarist Jonny Buckland."
        val sentence3 = "This third sentence should be ignored."
        val rawBio = "$sentence1 $sentence2 $sentence3 <a href=\"...\">Read more</a>"

        val formatted = viewModel.formatBio(rawBio)

        assertEquals("$sentence1 $sentence2", formatted)
        assertTrue(formatted.length <= 300)
    }

    @Test
    fun formatBio_excludesSecondSentence_whenCombinedExceeds300Chars() {
        val sentence1 = "Coldplay are a British rock band formed in London in 1997."
        val longSentence2 = "a".repeat(250) + "."
        val rawBio = "$sentence1 $longSentence2"

        val formatted = viewModel.formatBio(rawBio)

        assertEquals(sentence1, formatted)
        assertTrue(formatted.length <= 300)
    }

    @Test
    fun formatBio_truncatesSingleSentence_whenExceeds300Chars() {
        val longSentence = "This is a very long sentence " + "a".repeat(300)

        val formatted = viewModel.formatBio(longSentence)

        assertTrue(formatted.length <= 300)
    }

    @Test
    fun formatBio_stripsHtmlTagsAndUnescapesEntities() {
        val sentence1 = "Band formed in <b>London &amp; NY</b>."
        val sentence2 = "They released <i>Parachutes</i> in 2000."
        val rawBio = "$sentence1 $sentence2"

        val formatted = viewModel.formatBio(rawBio)

        assertEquals("Band formed in London & NY. They released Parachutes in 2000.", formatted)
    }

    @Test
    fun formatBio_handlesAbbreviationsWithoutSplittingSentence() {
        val sentence1 = "Coldplay was formed in the U.S. in 1997."
        val sentence2 = "They have sold over 1.5 million albums."
        val rawBio = "$sentence1 $sentence2"

        val formatted = viewModel.formatBio(rawBio)

        assertEquals("Coldplay was formed in the U.S. in 1997. They have sold over 1.5 million albums.", formatted)
    }
}
