//
// Swiss QR Bill Generator
// Copyright (c) 2017 Manuel Bleichenbacher
// Licensed under MIT License
// https://opensource.org/licenses/MIT
//
package net.codecrete.qrbill.generatortest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.codecrete.qrbill.canvas.FontMetrics;

public class FontMetricsTest {

    @Test
    public void shortOneLiner() {
        String[] lines = FontMetrics.splitLines("abc", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("abc", lines[0]);
    }

    @Test
    public void oneLiner() {
        String[] lines = FontMetrics.splitLines("abcdefghij", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("abcdefghij", lines[0]);
    }

    @Test
    public void oneLinerWithTwoWords() {
        String[] lines = FontMetrics.splitLines("abcdef ghij", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("abcdef ghij", lines[0]);
    }

    @Test
    public void leadingSpaceOneLiner() {
        String[] lines = FontMetrics.splitLines(" abcdefghij", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("abcdefghij", lines[0]);
    }

    @Test
    public void trailingSpaceOneLiner() {
        String[] lines = FontMetrics.splitLines("abcdefghij ", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("abcdefghij", lines[0]);
    }

    @Test
    public void emptyLine() {
        String[] lines = FontMetrics.splitLines("", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("", lines[0]);
    }

    @Test
    public void singleSpace() {
        String[] lines = FontMetrics.splitLines(" ", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("", lines[0]);
    }

    @Test
    public void manySpaces() {
        String[] lines = FontMetrics.splitLines("                           ", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("", lines[0]);
    }

    @Test
    public void outsideASCIIRange() {
        String[] lines = FontMetrics.splitLines("éà£$\uD83D\uDE03", 50, 10);
        assertEquals(1, lines.length);
        assertEquals("éà£$\uD83D\uDE03", lines[0]);
    }

    @Test
    public void twoLinesFromSpace() {
        String[] lines = FontMetrics.splitLines("abcde fghijk", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("abcde", lines[0]);
        assertEquals("fghijk", lines[1]);
    }

    @Test
    public void twoLinesFromNewLine() {
        String[] lines = FontMetrics.splitLines("abcde\nfghijk", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("abcde", lines[0]);
        assertEquals("fghijk", lines[1]);
    }

    @Test
    public void twoLinesWithTrailingNewline() {
        String[] lines = FontMetrics.splitLines("abcde\n", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("abcde", lines[0]);
        assertEquals("", lines[1]);
    }

    @Test
    public void singleNewline() {
        String[] lines = FontMetrics.splitLines("\n", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("", lines[0]);
        assertEquals("", lines[1]);
    }

    @Test
    public void spaceAndNewline() {
        String[] lines = FontMetrics.splitLines("  \n ", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("", lines[0]);
        assertEquals("", lines[1]);
    }

    @Test
    public void trailingAndLeadingSpaceAndNewline() {
        String[] lines = FontMetrics.splitLines(" abc \n", 50, 10);
        assertEquals(2, lines.length);
        assertEquals("abc", lines[0]);
        assertEquals("", lines[1]);
    }

    @Test
    public void forcedWorkbreak() {
        String[] lines = FontMetrics.splitLines("abcde", 2, 10);
        assertEquals(5, lines.length);
        assertEquals("a", lines[0]);
        assertEquals("b", lines[1]);
        assertEquals("c", lines[2]);
        assertEquals("d", lines[3]);
        assertEquals("e", lines[4]);
    }

    @Test
    public void forcedWordbreakWithSpaces() {
        String[] lines = FontMetrics.splitLines("  abcde  ", 2, 10);
        assertEquals(5, lines.length);
        assertEquals("a", lines[0]);
        assertEquals("b", lines[1]);
        assertEquals("c", lines[2]);
        assertEquals("d", lines[3]);
        assertEquals("e", lines[4]);
    }
}