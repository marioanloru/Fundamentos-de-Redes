//Servicio online de citas
//(CC) Mario Lopez, Antonio Rodriguez, 2017)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CitasClienteTCP {

	public static void main(String[] args) {

		String buferEnvio;
		String buferRecepcion;
		String error = "ERROR/";


		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;

		// Socket para la conexión TCP
		Socket socketServicio=null;

		try {
			// Creamos un socket que se conecte a "host" y "port":
			socketServicio = new Socket(host, port);

			InputStream inputStream = socketServicio.getInputStream();
			OutputStream outputStream = socketServicio.getOutputStream();

			//Mensaje de bienvenida
			System.out.println("************Bienvenido al sistema de citas online************ \n\nLos datos necesarios se irán pidiendo por pantalla.");
			System.out.println("Para salir en cualquier momento pulse la tecla 'Q'");

			//Autenticacion
			System.out.println("\nLOGIN: (usuario-contraseña) (por defecto fr-finisterre)");

			PrintWriter outprinter = new PrintWriter(outputStream,true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));

			//Solicito el login por pantalla
			Scanner terminalInput = new Scanner(System.in);
			buferEnvio = terminalInput.nextLine();


			// Enviamos el array por el outputStream;
			outprinter.println(buferEnvio);
			outprinter.flush();

			//////////////////////////////////////////////////////

			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			buferRecepcion = inReader.readLine();

			//Se solicita la entrada hasta que sea correcta o se haga logout con "q"
			while(buferRecepcion.toLowerCase().contains(error.toLowerCase()) ){
				if(buferEnvio.toLowerCase().equals("q"))
					System.exit(0);

				System.out.println("Login incorrecto, vuelva a introducirlo:");
				buferEnvio = terminalInput.nextLine();

				outprinter.println(buferEnvio);
				outprinter.flush();


				buferRecepcion = inReader.readLine();

			}

			//Respuesta Autenticacion
			System.out.print(buferRecepcion);
			System.out.println("\n");

			//Seleccion del centro
			// Mostremos los datos del centro recibido:
			buferRecepcion = inReader.readLine();
			System.out.print(buferRecepcion);
			System.out.println("\n");

			buferEnvio = terminalInput.nextLine();

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();

			//Se solicita la entrada hasta que sea correcta o se haga logout
			while(buferRecepcion.toLowerCase().contains(error.toLowerCase())){
				if(buferEnvio.toLowerCase().equals("q"))
					System.exit(0);

				System.out.println("Centro incorrecto, vuelva a introducir los datos:");
				buferEnvio = terminalInput.nextLine();
				outprinter.println(buferEnvio);
				outprinter.flush();

				buferRecepcion = inReader.readLine();

			}

			//Datos de la fecha
			System.out.print(buferRecepcion);
			System.out.println("\n");


			//Seleccion de la fecha
			buferEnvio = terminalInput.nextLine();

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();


						//Se solicita la entrada hasta que sea correcta o se haga logout
			while(buferRecepcion.toLowerCase().contains(error.toLowerCase())){
				if(buferEnvio.toLowerCase().equals("q"))
					System.exit(0);

				System.out.println("Fecha incorrecta, vuelva a introducir los datos:");
				buferEnvio = terminalInput.nextLine();
				outprinter.println(buferEnvio);
				outprinter.flush();

				buferRecepcion = inReader.readLine();

			}

			//Informacion de las horas disponibles
			System.out.print(buferRecepcion);
			System.out.println("\n");

			//Seleccion de la hora
			buferEnvio = terminalInput.nextLine();

			outprinter.println(buferEnvio);
			outprinter.flush();

			buferRecepcion = inReader.readLine();


		  //Se solicita la entrada hasta que sea correcta o se haga logout
			while(buferRecepcion.toLowerCase().contains(error.toLowerCase())){
				if(buferEnvio.toLowerCase().equals("q"))
					System.exit(0);

				System.out.println("Hora incorrecta, vuelva a introducir los datos:");
				buferEnvio = terminalInput.nextLine();
				outprinter.println(buferEnvio);
				outprinter.flush();

				buferRecepcion = inReader.readLine();

			}

			//Hora seleccionada
			System.out.println("\n");
			System.out.print(buferRecepcion);
			System.out.println("\n");

			//Todos los datos de la cita
			buferRecepcion = inReader.readLine();
			System.out.print(buferRecepcion);
			System.out.print("\n");



			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			socketServicio.close();

			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
