package com.task.model;

import java.util.Map;
import java.util.Set;

public interface IElementLead extends IElement {

  Map<String, IElementContent> getContent();

  default void addContent(IElementContent content) {
    Map<String, IElementContent> contentMap = this.getContent();
    if (!contentMap.containsValue(content)) {
      contentMap.put(content.getHash(), content);
    }

  }

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
        showContent(result, walked, (IElementLead) element);
      }
    }
    walked.add(lead);
  }

  default void getElements(Set<IElement> walked, IElementLead lead) {
    if ((lead == null) || walked.contains(lead)) {
      return;
    }
    for (IElement element : lead.getContent().values()) {
      if (element instanceof Blob) {
        walked.add(element);
      } else {
        getElements(walked, (IElementLead) element);
      }
    }
    walked.add(lead);
  }
}
