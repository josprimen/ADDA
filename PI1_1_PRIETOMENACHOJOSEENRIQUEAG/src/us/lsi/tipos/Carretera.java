  package us.lsi.tipos;

  import org.jgraph.graph.DefaultEdge;  
  
  import us.lsi.graphs.StringEdgeFactory;  
  
  public class Carretera extends DefaultEdge {  
  
          /**  
           *   
           */  
          private static final long serialVersionUID = 1L;  
  
          public static StringEdgeFactory<Ciudad, Carretera> factoria = new Factoria();
          private Double coste = null;
  
          public Carretera(Ciudad c1, Ciudad c2, Double coste) {  
                  super();  
                  super.source = c1;  
                  super.target = c2;  
                  this.coste = coste;
          }  
  
            
            
          @Override  
          public String toString() {  
                  return "[" +super.source + "," + super.target + "]";  
          }  
  
  
  
          public Double getCoste() {
			return coste;
		  }



		private static class Factoria implements  StringEdgeFactory<Ciudad, Carretera> {  
  
                  public Factoria() {  
                          super();  
                          // TODO Auto-generated constructor stub  
                  }  

                  @Override  
                  public Carretera createEdge(Ciudad c1, Ciudad c2, String[] formato) {  
                          // TODO Auto-generated method stub
                	      Carretera c = null;
                	      if (formato!=null && formato.length>2) {
                	    	  c = new Carretera(c1, c2, Double.valueOf(formato[2]));
                	      } else {
                              c = new Carretera(c1, c2, -1.0);
                	      }
                	      return c;
                  }

				@Override
				public Carretera createEdge(Ciudad arg0, Ciudad arg1) {
					// TODO Auto-generated method stub
					return createEdge(arg0,arg1,null);  
				}  
          }  
  }  




























































       