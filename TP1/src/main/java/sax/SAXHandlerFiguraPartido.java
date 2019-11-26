package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandlerFiguraPartido extends DefaultHandler {
    private int nFigura;
    private boolean hayFigura;

    public SAXHandlerFiguraPartido() { //constructor
        this.nFigura=0;
        this.hayFigura=false;
    }
    // Getter
    public int getnFigura() {
        return nFigura;
    }

    // Setter
    public void setnFigura(int nFigura) {
        this.nFigura = nFigura;
    }

    // Getter
    public boolean gethayFigura() {
        return hayFigura;
    }

    // Setter
    public void sethayFigura(boolean hayFigura) {
        this.hayFigura = hayFigura;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("figura")){
            this.nFigura++;
            System.out.print(this.nFigura + "- ");
            this.hayFigura = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(this.hayFigura){
            System.out.print(new String(ch, start, length) + "." + "\n");
            this.hayFigura = false;
        }

    }

}