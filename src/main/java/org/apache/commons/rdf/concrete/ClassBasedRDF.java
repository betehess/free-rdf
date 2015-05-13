package org.apache.commons.rdf.concrete;

import org.apache.commons.rdf.api.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;

/** An RDF model (still abstract) that manipulates concrete
 * interfaces.
 *
 * Basically, the methods that are left abstract are exactly the ones
 * from the RDFTermFactory.
 */
public abstract class ClassBasedRDF implements RDF<Graph, Triple, RDFTerm, BlankNodeOrIRI, IRI, BlankNode, Literal> {

    public Graph add(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
        graph.add(subject, predicate, object);
        return graph;
    }
    public Graph add(Graph graph, Triple triple) {
        graph.add(triple);
        return graph;
    }
    public Graph remove(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
        graph.remove(subject, predicate, object);
        return graph;
    }
    public boolean contains(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
        return graph.contains(subject, predicate, object);
    }
    public Stream<? extends Triple> 	getTriplesAsStream(Graph graph) {
        return graph.getTriples();
    }
    public Iterable<Triple> getTriplesAsIterable(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
        return graph.iterate(subject, predicate, object);
    }
    public long size(Graph graph) {
        return graph.size();
    }
    public <T> T visit(RDFTerm t, Function<IRI, T> fIRI, Function<BlankNode, T> fBNode, Function<Literal, T> fLiteral) {
        if (t instanceof IRI) {
            return fIRI.apply((IRI)t);
        } else if (t instanceof BlankNode) {
            return fBNode.apply((BlankNode)t);
        } else /* if (t instanceof IRI) */ {
            return fLiteral.apply((Literal)t);
        }
    }
    public String getIRIString(IRI iri) {
        return iri.getIRIString();
    }
    public String uniqueReference(BlankNode bnode) {
        return bnode.uniqueReference();
    }
    public IRI getDatatype(Literal literal) {
        return literal.getDatatype();
    }
    public Optional<String> getLanguageTag(Literal literal) {
        return literal.getLanguageTag();
    }
    public String getLexicalForm(Literal literal) {
        return literal.getLexicalForm();
    }

}
