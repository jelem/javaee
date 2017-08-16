package com.task.model;

import javax.naming.OperationNotSupportedException;
import java.util.Map;
import java.util.Set;

public interface IElementLead extends IElement {

    Map<String, IElementContent> getContent();

    void addContent(IElementContent content);

    default void showContent(StringBuilder result, Set<IElementLead> walked, IElementLead lead) {
        if ((lead == null) || (walked.contains(lead))) {
            return;
        }
        result.append("\n");
        result.append(lead.getInfo());
        for (IElement element : lead.getContent().values()) {
            if (element instanceof Blob) {
                result.append("\n");
                result.append(element.getInfo());
            } else {
                showContent(result, walked, (IElementLead)element);
            }
        }
        walked.add(lead);
    }
}
