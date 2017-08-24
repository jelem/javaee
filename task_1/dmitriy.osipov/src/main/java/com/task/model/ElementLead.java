package com.task.model;

import java.util.Map;
import java.util.Set;

public interface ElementLead extends Element {

  Map<String, ElementContent> getContent();

  default void addContent(ElementContent content) {
    Map<String, ElementContent> contentMap = this.getContent();
    if (!contentMap.containsValue(content)) {
      contentMap.put(content.getHash(), content);
    }

  }

  default void showContent(StringBuilder result, Set<ElementLead> walked, ElementLead lead) {
    if ((lead == null) || (walked.contains(lead))) {
      return;
    }
    result.append("\n");
    result.append(lead.getInfo());
    for (Element element : lead.getContent().values()) {
      if (element instanceof Blob) {
        result.append("\n");
        result.append(element.getInfo());
      } else {
        showContent(result, walked, (ElementLead) element);
      }
    }
    walked.add(lead);
  }

  default void getElements(Set<Element> walked, ElementLead lead) {
    if ((lead == null) || walked.contains(lead)) {
      return;
    }
    for (Element element : lead.getContent().values()) {
      if (element instanceof Blob) {
        walked.add(element);
      } else {
        getElements(walked, (ElementLead) element);
      }
    }
    walked.add(lead);
  }
}
