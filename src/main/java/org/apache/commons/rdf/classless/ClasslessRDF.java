package org.apache.commons.rdf.classless;

import java.util.UUID;
import org.apache.commons.rdf.api.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;
import java.net.URI;

/**
 * Not really classless... But there are no shared inheritance but Object.
 *
 * Obviously, the implementations is buggy and incomplete. But you get the idea.
 */
public class ClasslessRDF implements RDF<Set<Triple>, Triple, Object, Object, URI, UUID, String> {

    @Override
    public UUID createBlankNode() {
        return UUID.randomUUID();
    }

    @Override
    public UUID createBlankNode(String name) {
        return UUID.randomUUID();
    }

    @Override
    public Set<Triple> createGraph() {
        return new HashSet();
    }

    @Override
    public URI createIRI(String iri) {
        try {
            return new URI(iri);
        } catch (java.net.URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String createLiteral(String literal) {
        return literal;
    }

    @Override
    public String createLiteral(String literal, URI dataType) {
        return literal + "^^" + getIRIString(dataType);
    }

    @Override
    public String createLiteral(String literal, String language) {
        return literal + "@" + language;
    }

    @Override
    public Triple createTriple(Object subject, URI predicate,
                               Object object) {
        return new Triple(subject, predicate, object);
    }


    //////////////

    public Set<Triple> add(Set<Triple> graph, Object subject, URI predicate, Object object) {
        Set<Triple> set = new HashSet<Triple>(graph);
        set.add(createTriple(subject, predicate, object));
        return set;
    }
    public Set<Triple> add(Set<Triple> graph, Triple triple) {
        Set<Triple> set = new HashSet<Triple>(graph);
        set.add(triple);
        return set;
    }
    public Set<Triple> remove(Set<Triple> graph, Object subject, URI predicate, Object object) {
        Set<Triple> set = new HashSet<Triple>(graph);
        set.remove(createTriple(subject, predicate, object));
        return set;
    }
    public boolean contains(Set<Triple> graph, Object subject, URI predicate, Object object) {
        return graph.contains(createTriple(subject, predicate, object));
    }
    public Stream<? extends Triple> getTriplesAsStream(Set<Triple> graph) {
        return null; // boring
    }
    public Iterable<Triple> getTriplesAsIterable(Set<Triple> graph, Object subject, URI predicate, Object object) {
        return null; // boring
    }
    public long size(Set<Triple> graph) {
        return graph.size();
    }
    public Object getSubject(Triple triple) {
        return triple.getSubject();
    }
    public URI getPredicate(Triple triple) {
        return triple.getPredicate();
    }
    public Object getObject(Triple triple) {
        return triple.getObject();
    }
    public <T> T visit(Object t, Function<URI, T> fIRI, Function<UUID, T> fBNode, Function<String, T> fLiteral) {
        if (t instanceof URI) {
            return fIRI.apply((URI)t);
        } else if (t instanceof UUID) {
            return fBNode.apply((UUID)t);
        } else /* if (t instanceof String) */ {
            return fLiteral.apply((String)t);
        }
    }
    public String getIRIString(URI iri) {
        return iri.toString();
    }
    public String uniqueReference(UUID bnode) {
        return bnode.toString();
    }
    public URI getDatatype(String literal) {
        return createIRI(literal.split("^^")[1]);
    }
    public Optional<String> getLanguageTag(String literal) {
        return null; // boring
    }
    public String getLexicalForm(String literal) {
        return null; // boring
    }

}
