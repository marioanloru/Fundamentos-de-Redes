
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CitasClienteTCP {

	public static void main(String[] args) {

		//byte []buferEnvio;
		String buferEnvio;
		String buferRecepcion;
		//byte []buferRecepcion=new byte[256];
		//int bytesLeidos=0;

		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;

		// Socket para la conexión TCP
		Socket socketServicio=null;

		try {
			// Creamos un socket que se conecte a "host" y "port":
			//////////////////////////////////////////////////////
			socketServicio = new Socket(host, port);
			// socketServicio= ... (Completar)
			//////////////////////////////////////////////////////

			InputStream inputStream = socketServicio.getInputStream();
			OutputStream outputStream = socketServicio.getOutputStream();

			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			//Autenticacion
			buferEnvio="usuario-1234";

			// Enviamos el array por el outputStream;
			//////////////////////////////////////////////////////
			PrintWriter outprinter = new PrintWriter(outputStream,true);

			outprinter.println(buferEnvio);
			//outputStream.write(buferEnvio,0,buferEnvio.length);

			// ... .write ... (Completar)
			//////////////////////////////////////////////////////

			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			//////////////////////////////////////////////////////
			outprinter.flush();
			// ... .flush(); (Completar)
			//////////////////////////////////////////////////////

			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			//////////////////////////////////////////////////////
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			buferRecepcion = inReader.readLine();
			//bytesLeidos = inputStream.read(buferRecepcion);
			// bytesLeidos ... .read... buferRecepcion ; (Completar)
			//////////////////////////////////////////////////////
			//Seleccion del centro


			// MOstremos la cadena de caracteres recibidos:
			System.out.println("LOGIN. ");
			System.out.print(buferRecepcion);
			System.out.println("\n");


			//Selecion del centro
			buferEnvio = "Facultad de Ciencias de la Educacion";

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();


			System.out.print(buferRecepcion);
			System.out.println("\n");


			//Seleccion de la fecha
			buferEnvio = "10/11/2017";

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();

			System.out.println("\n");
			System.out.print(buferRecepcion);
			System.out.println("\n");

			//Seleccion de la hora
			buferEnvio = "09:00";

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();

			System.out.println("\n");
			System.out.print(buferRecepcion);
			System.out.println("\n");

			buferRecepcion = inReader.readLine();
			System.out.print(buferRecepcion);
			System.out.print("\n");

			//Todos los datos de la cita

			buferRecepcion = inReader.readLine();

			System.out.print("\n");
			System.out.print(buferRecepcion);
			System.out.print("\n");


			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socketServicio.close();
			// ... close(); (Completar)
			//////////////////////////////////////////////////////

			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}