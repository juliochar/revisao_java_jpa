
package br.com.caelum.livraria.clientews;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.caelum.livraria.clientews package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetLivrosPeloNomeResponse_QNAME = new QName("http://webservice.livraria.caelum.com.br/", "getLivrosPeloNomeResponse");
    private final static QName _GetLivrosPeloNome_QNAME = new QName("http://webservice.livraria.caelum.com.br/", "getLivrosPeloNome");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.caelum.livraria.clientews
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLivrosPeloNome }
     * 
     */
    public GetLivrosPeloNome createGetLivrosPeloNome() {
        return new GetLivrosPeloNome();
    }

    /**
     * Create an instance of {@link GetLivrosPeloNomeResponse }
     * 
     */
    public GetLivrosPeloNomeResponse createGetLivrosPeloNomeResponse() {
        return new GetLivrosPeloNomeResponse();
    }

    /**
     * Create an instance of {@link Autor }
     * 
     */
    public Autor createAutor() {
        return new Autor();
    }

    /**
     * Create an instance of {@link Livro }
     * 
     */
    public Livro createLivro() {
        return new Livro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLivrosPeloNomeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.livraria.caelum.com.br/", name = "getLivrosPeloNomeResponse")
    public JAXBElement<GetLivrosPeloNomeResponse> createGetLivrosPeloNomeResponse(GetLivrosPeloNomeResponse value) {
        return new JAXBElement<GetLivrosPeloNomeResponse>(_GetLivrosPeloNomeResponse_QNAME, GetLivrosPeloNomeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLivrosPeloNome }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.livraria.caelum.com.br/", name = "getLivrosPeloNome")
    public JAXBElement<GetLivrosPeloNome> createGetLivrosPeloNome(GetLivrosPeloNome value) {
        return new JAXBElement<GetLivrosPeloNome>(_GetLivrosPeloNome_QNAME, GetLivrosPeloNome.class, null, value);
    }

}
