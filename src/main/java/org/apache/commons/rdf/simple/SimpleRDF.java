/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.rdf.simple;

import java.util.UUID;
import org.apache.commons.rdf.concrete.*;
import org.apache.commons.rdf.api.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class SimpleRDF implements RDF<Graph, Triple, RDFTerm, BlankNodeOrIRI, IRI, BlankNode, Literal> {

    /** Unique salt per instance, for {@link #createBlankNode(String)}
     */
    private final UUID SALT = UUID.randomUUID();

    @Override
    public BlankNode createBlankNode() {
        return new BlankNodeImpl();
    }

    @Override
    public BlankNode createBlankNode(String name) {
        return new BlankNodeImpl(SALT, name);
    }

    @Override
    public Graph createGraph() {
        return new GraphImpl(this);
    }

    @Override
    public IRI createIRI(String iri) {
        IRI result = new IRIImpl(iri);
        return Types.get(result).orElse(result);
    }

    @Override
    public Literal createLiteral(String literal) {
        return new LiteralImpl(literal);
    }

    @Override
    public Literal createLiteral(String literal, IRI dataType) {
        return new LiteralImpl(literal, dataType);
    }

    @Override
    public Literal createLiteral(String literal, String language) {
        return new LiteralImpl(literal, language);
    }

    @Override
    public Triple createTriple(BlankNodeOrIRI subject, IRI predicate,
                               RDFTerm object) {
        return new TripleImpl(subject, predicate, object);
    }


    //////////////

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
    public BlankNodeOrIRI getSubject(Triple triple) {
        return triple.getSubject();
    }
    public IRI getPredicate(Triple triple) {
        return triple.getPredicate();
    }
    public RDFTerm getObject(Triple triple) {
        return triple.getObject();
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
