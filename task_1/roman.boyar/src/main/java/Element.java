import java.util.ArrayList;

public class Element {

    /*
       Типы элементов, которые могут быть в Map в качестве значений:
commit (содержит хэш значение, которое является ключом для какого-то другого элемента)
tree (то же, что и commit)
blob (какой-то текст, можно представить обычной строкой)
    */
    enum TYPE {
        commit,
        tree,
        blob
    }

    private String name;//name
    private TYPE type;//type
    private String hash;
    private String content;
    private ArrayList<Element> childs = new ArrayList();
    private Element parent;

    public ArrayList<Element> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Element> childs) {
        this.childs = childs;
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", hash='" + hash + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}




