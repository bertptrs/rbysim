package nl.solutionweb.rbysim.moves;

import com.google.common.collect.ForwardingMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 * @author Bert Peters
 */
@XmlRootElement(name = "movedatabase")
public class MoveDatabase extends ForwardingMap<String, Move> {

    @XmlElement(name = "move")
    private List<Move> moves;

    private final Map<String, Move> moveIndex = new HashMap<>();

    private MoveDatabase() throws IOException {
    }

    public static MoveDatabase instance() throws IOException {
        try(InputStream datasource = MoveDatabase.class.getResourceAsStream("moves.xml")) {
            JAXBContext jaxbc = JAXBContext.newInstance(MoveDatabase.class);
            Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
            MoveDatabase database = (MoveDatabase) unmarshaller.unmarshal(datasource);
            database.index();

            return database;
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }
    }

    private void index() {
        for (Move move : moves) {
            put(move.getName().toLowerCase(), move);
        }
    }

    @Override
    protected Map<String, Move> delegate() {
        return moveIndex;
    }
}
