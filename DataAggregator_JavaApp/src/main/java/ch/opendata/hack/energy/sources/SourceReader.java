package ch.opendata.hack.energy.sources;

import java.util.List;

/**
 * The interface Source reader.
 */
public interface SourceReader {

    /**
     * Read source list.
     *
     * @return the list
     */
    List<RawDataObject> readSource();
}
