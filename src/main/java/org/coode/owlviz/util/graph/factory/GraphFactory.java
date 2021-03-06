package org.coode.owlviz.util.graph.factory;

import org.coode.owlviz.util.graph.graph.Graph;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Dec 27, 2003<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 * <p/>
 * Defines the interface required of a <code>GraphFactory</code>
 */
public interface GraphFactory {

    /**
     * Creates a <code>Graph</code>
     *
     * @return The newly created <code>Graph</code>
     */
    public Graph createGraph();
}
