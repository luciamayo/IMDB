package co.empathy.academy.searchmyexample.service;

import static org.apache.lucene.analysis.compound.hyphenation.TernaryTree.strcmp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {



   /* @Test
    void givenNumVersion_whenGetVersion_thenReturnNonZeroNumFound()
    {
        ElasticService searchEngine = mock(ElasticService.class);
        given(searchEngine.getVersion()).willReturn("2.15.2");

        ElasticService searchService = new ElasticService();

        String numVersion = searchService.getVersion();

        assertEquals("2.15.2", numVersion);
    }*/


}
