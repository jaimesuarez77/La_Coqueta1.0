package clases;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Archivo {
    
    private String codigo;
    private String nombre;
    private String tipo;
    private String precio;
    
    
    //variable boll
    private boolean encontro=false;
    //variable para manipular el archivo
    private FileWriter escritor=null;
    private PrintWriter pw=null;
    private File archivo=null;
    private FileReader lector=null;
    private BufferedReader br=null;
    
    //vector para separar los campos del archivo ´plano
    String [] cadena;

    public Archivo() {
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    
    public boolean getEncontro(){
       return  this.encontro;
    }
    
    public void escribir(String cod,String nom,String tipo,String precio, String can,String to){
        try{
            escritor=new FileWriter("./src/recursos/venta.txt",true);
            pw=new PrintWriter(escritor);
            pw.println(cod + "," + nom +"," + tipo + "," + precio + "," + can + "," + to);
            JOptionPane.showMessageDialog(null, "Se ha hecho la compra de la boleta");
            
            escritor.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
    
    }//cierre del metodo
    
    
    public void buscar(String cod){
    
         try{
            archivo=new File("./src/recursos/eventos.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                if(cadena[0].equalsIgnoreCase(cod)){
                    encontro=true;
                    break;
                }else{
                    encontro=false;
                }
            
            
            }//cierre while
            if(encontro){
                this.codigo=cadena[0];
                this.nombre=cadena[1];
                this.tipo = cadena[2];
                this.precio  = cadena[3];
            
            }else{
                this.codigo="";
                this.nombre="";
                this.tipo="";
                this.precio="";
             
                JOptionPane.showMessageDialog(null, "El evento no esta registrado");
            }
            
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
    
    
    }//fin del metodo
    
    //metodo listar
    public List<String[]> listado(){
        List<String[]> datos=new ArrayList<>(); 
         try{
            archivo=new File("./src/recursos/eventos.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                datos.add(cadena);
            
            }//cierre while
           
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
         return datos;
    }
     public List<String[]> listadoVentas(){
        List<String[]> datosV=new ArrayList<>(); 
         try{
            archivo=new File("./src/recursos/venta.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                datosV.add(cadena);
                
            
            }//cierre while
           
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
         return datosV;
    }
     
     public void calcularMusicalesYTotal() {
    List<String[]> datosVentas = listadoVentas();
    int contadorMusicales = 0;
    double sumaTotal = 0.0;

    for (String[] fila : datosVentas) {
        if (fila.length >= 6) { // Asegurar que la fila tiene suficientes columnas
            String tipo = fila[2].trim();
            String totalStr = fila[5].trim();

            if (tipo.equalsIgnoreCase("musical")) {
                contadorMusicales++;
            }

            try {
                double total = Double.parseDouble(totalStr);
                sumaTotal += total;
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir a número: " + totalStr);
            }
        }
    }

    System.out.println("Cantidad de eventos musicales: " + contadorMusicales);
    System.out.println("Suma total de la columna 'Total': " + sumaTotal);
}

    
    
    
    
    
}//
