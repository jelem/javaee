package com.task.model;

import javax.naming.OperationNotSupportedException;
import java.util.Map;
import java.util.Set;

public interface IElementLead extends IElement {

    Map<String, IElementContent> getContent();

    void addContent(IElementContent content);

    default void showContent(StringBuilder result, Set<IElementLead> walked, IElementLead lead) {
        if (lead == null || walked.contains(lead)) {
            return;
        }
        walked.add(lead);
        result.append(String.format("\n\t%s", lead.getInfo()));
        Map<String, IElementContent> contentMap = lead.getContent();
        if (contentMap != null) {
            for (IElementContent content : contentMap.values()) {
                if (content instanceof IElementLead) {
                    result.append("\n\t\t");
                    showContent(result, walked, (IElementLead) content);
                }
                else {
                    result.append(String.format("\n\t%s", content.getInfo()));
                }
            }
        }
        //return result;
    }
}
