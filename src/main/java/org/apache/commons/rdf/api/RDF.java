package org.apache.commons.rdf.api;

import java.util.stream.*;
import java.util.function.*;
import java.util.*;

// this contract is mainly for library implementors, not that much for
// users. Well, this can be argued :-). Another side-effect of this
// approach is that code using the RDF interface would create and see
// the actual types/classes being manipulated, not the common
// interfaces.
public interface RDF<Graph,
                     Triple,
                     RDFTerm,
                     BlankNodeOrIRI extends RDFTerm,
                     IRI extends BlankNodeOrIRI,
                     BlankNode extends BlankNodeOrIRI,
                     Literal extends RDFTerm> {
    /* RDFTermFactory */
    BlankNode createBlankNode();
    BlankNode createBlankNode(String name);
    Graph createGraph();
    IRI createIRI(String iri) throws IllegalArgumentException;
    Literal createLiteral(String lexicalForm) throws IllegalArgumentException;
    Literal createLiteral(String lexicalForm, IRI dataType) throws IllegalArgumentException;
    Literal createLiteral(String lexicalForm, String languageTag) throws IllegalArgumentException;
    Triple createTriple(BlankNodeOrIRI subject, IRI predicate, RDFTerm object) throws IllegalArgumentException;
    /* Graph */
    // note that Graph is abstract, so that means that this handles
    // both the mutable and immutable cases, as the implementer will
    // be able to choose what Graph actually is.
    Graph add(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object);
    Graph add(Graph graph, Triple triple);
    Graph remove(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object);
    // no clear() or close(), as this is specific to a type, so it doesn't belong here
    boolean contains(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object);
    Stream<? extends Triple> 	getTriplesAsStream(Graph graph);
    Iterable<Triple> getTriplesAsIterable(Graph graph, BlankNodeOrIRI subject, IRI predicate, RDFTerm object);
    long size(Graph graph);
    /* Triple */
    BlankNodeOrIRI getSubject(Triple triple);
    IRI getPredicate(Triple triple);
    RDFTerm getObject(Triple triple);
    /* RDFTerm */
    // this is very much like a Visitor. Note that this would work
    // even for RDFTerm =:= String and would still be type-safe. I beg that we do not rely on
    // instanceof and/or isURI/isBNode/isLiteral.
    <T> T visit(RDFTerm t, Function<IRI, T> fIRI, Function<BlankNode, T> fBNode, Function<Literal, T> fLiteral);
    /* IRI */
    String getIRIString(IRI iri);
    /* BlankNode */
    String uniqueReference(BlankNode bnode);
    /* Literal */
    IRI getDatatype(Literal literal);
    Optional<String> getLanguageTag(Literal literal);
    String getLexicalForm(Literal literal);
}
