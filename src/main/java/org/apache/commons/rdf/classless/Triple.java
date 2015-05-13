package org.apache.commons.rdf.classless;

import java.net.URI;
import java.util.Objects;

final class Triple {

    private final Object subject;
    private final URI predicate;
    private final Object object;

    public Triple(Object subject, URI predicate, Object object) {
        this.subject = Objects.requireNonNull(subject);
        this.predicate = Objects.requireNonNull(predicate);
        this.object = Objects.requireNonNull(object);
    }

    public Object getSubject() {
        return subject;
    }

    public URI getPredicate() {
        return predicate;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "@@@@@@@@";
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, predicate, object);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple other = (Triple) obj;
        return getSubject().equals(other.getSubject())
                && getPredicate().equals(other.getPredicate())
                && getObject().equals(other.getObject());
    }

}
