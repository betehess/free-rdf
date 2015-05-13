package org.apache.commons.rdf.turtle;

import org.apache.commons.rdf.api.*;

/**
 * Granted, there is a little dance that a library author would have
 * to do. There are different ways to improve that.
 *
 * Just remember one thing: all the types here are abstract!
 */
public class Turtle<Graph, Triple, RDFTerm, BlankNodeOrIRI extends RDFTerm, IRI extends BlankNodeOrIRI, BlankNode extends BlankNodeOrIRI, Literal extends RDFTerm> {

    private RDF<Graph, Triple, RDFTerm, BlankNodeOrIRI, IRI, BlankNode, Literal> rdf;

    Turtle(RDF<Graph, Triple, RDFTerm, BlankNodeOrIRI, IRI, BlankNode, Literal> rdf) {
        this.rdf = rdf;
    }

    /* a very silly parser */
    public Graph parse(String input) {
        Triple triple =
            rdf.createTriple(
                             rdf.createIRI("http://example.com/Alice"),
                             rdf.createIRI("http://example.com/name"),
                             rdf.createLiteral("Alice"));
        Graph graph = rdf.createGraph();
        return rdf.add(graph, triple);
    }

    /* a very silly serializer */
    public String serialize(Graph graph) {
        Triple triple = rdf.getTriplesAsIterable(graph, null, null, null).iterator().next();
        RDFTerm o = rdf.getObject(triple);
        return rdf.visit(o,
                         iri -> rdf.getIRIString(iri),
                         bn -> rdf.uniqueReference(bn),
                         lit -> rdf.getLexicalForm(lit));
    }
    
}
