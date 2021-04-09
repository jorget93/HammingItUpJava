package zipcode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zipcode.Hamming;

import static org.junit.Assert.assertEquals;


public class HammingTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNoDistanceBetweenEmptyStrands() throws Exception {
        assertEquals(0, new Hamming("", "").getHammingDistance());
    }

    @Test
    public void testNoDistanceBetweenShortIdenticalStrands() throws Exception {
        assertEquals(0, new Hamming("A", "A").getHammingDistance());
    }

    @Test
    public void testNoDistanceBetweenLongIdenticalStrands() throws Exception {
        assertEquals(0, new Hamming("GGACTGA", "GGACTGA").getHammingDistance());
    }

    @Test
    public void testCompleteDistanceInSingleNucleotideStrand() throws Exception {
        assertEquals(1, new Hamming("A", "G").getHammingDistance());
    }

    @Test
    public void testCompleteDistanceInSmallStrand() throws Exception {
        assertEquals(2, new Hamming("AG", "CT").getHammingDistance());
    }

    @Test
    public void testSmallDistanceInSmallStrand() throws Exception {
        assertEquals(1, new Hamming("AT", "CT").getHammingDistance());
    }

    @Test
    public void testSmallDistanceInMediumStrand() throws Exception {
        assertEquals(1, new Hamming("GGACG", "GGTCG").getHammingDistance());
    }

    @Test
    public void testSmallDistanceInLongStrand() throws Exception {
        assertEquals(2, new Hamming("ACCAGGG", "ACTATGG").getHammingDistance());
    }

    @Test
    public void testNonUniqueCharacterInFirstStrand() throws Exception {
        assertEquals(1, new Hamming("AAG", "AAA").getHammingDistance());
    }

    @Test
    public void testNonUniqueCharacterInSecondStrand() throws Exception {
        assertEquals(1, new Hamming("AAA", "AAG").getHammingDistance());
    }

    @Test
    public void testSameNucleotidesInDifferentPositions() throws Exception {
        assertEquals(2, new Hamming("TAG", "GAT").getHammingDistance());
    }

    @Test
    public void testLargeDistanceInPermutedStrand() throws Exception {
        assertEquals(4, new Hamming("GATACA", "GCATAA").getHammingDistance());
    }

    @Test
    public void testLargeDistanceInOffByOneStrand() throws Exception {
        assertEquals(9, new Hamming("GGACGGATTCTG", "AGGACGGATTCT").getHammingDistance());
    }

    @Test
    public void testValidatesFirstStrandNotLonger() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("leftStrand and rightStrand must be of equal length.");

        new Hamming("AATG", "AAA");
    }

    @Test
    public void testValidatesSecondStrandNotLonger() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("leftStrand and rightStrand must be of equal length.");

        new Hamming("ATA", "AGTG");
    }

}
