package cop.project.order.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class DbSequenceTest {

    @Test
    public void testGetterAndSetterAndToString() {

        DbSequence dbSequence = new DbSequence();
        String id = "one";
        Integer seqNo = 1;

        assertNull(dbSequence.getId());
        assertNull(dbSequence.getSeqNo());

        dbSequence.setId(id);
        dbSequence.setSeqNo(seqNo);

        assertEquals(id, dbSequence.getId());
        assertEquals(seqNo, dbSequence.getSeqNo());

        String expectedString = "DbSequence(id=one, seqNo=1)";
        assertEquals(expectedString, dbSequence.toString());

    }
}
