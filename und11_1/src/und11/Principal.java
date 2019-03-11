package und11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class Principal {

	public static void main(String[] args) {
		Map<String,Alumno>alumnos=new TreeMap<String,Alumno>();
		int opc;
		do {
			opc=menu();
			switch(opc) {
			case 1:
				alta(alumnos);
				break;
			case 2:
				listar(alumnos);
				break;
			case 3:
				System.out.println("Esta opción no se puede");
				break;
			case 4:
				alumnos.clear();
				break;
			case 5:
				borrar(alumnos);
				break;
			case 6:
				modificar(alumnos);
				break;
			case 7:
				listar2(alumnos);
				break;
			case 8:
				nomApe(alumnos);
				break;
			case 9:
				listar9(alumnos);
				break;
			}
		}while(opc!=10);


	}

	private static int menu() {
		System.out.println("//////////////////MENU/////////////////");
		System.out.println("1.alta");
		System.out.println("2.Listar alumnos");
		System.out.println("3.Ordenar");
		System.out.println("4.Borrar todos");
		System.out.println("5.Borrar alumno");
		System.out.println("6.Modificar alumno");
		System.out.println("7.Listar por annio");
		System.out.println("8.Mostrar por año");
		System.out.println("9.Tercer listado");
		System.out.println("10.Salir");
		return Util.leerInt(1,10);
	}

	private static void alta(Map<String, Alumno> alumnos) {
		char opc;
		do {

			Alumno aux=new Alumno();
			aux.setDatos();
			alumnos.put(aux.getDni(), aux);
			System.out.println("Quieres introducir más alumnos?");
			opc=Util.respCharList("SN");
		}while(opc=='S');

	}

	private static void listar(Map<String, Alumno> alumnos) {
		//For
		/*
		for(int i=0;i<alumnos.size();i++) {
			alumnos.get(i).getDatos();
		}
		*/
		//For-each
		/*
		 for(Alumno a:alumnos) {
			a.getDatos();
		}
		 */
		//iterartor
		
		Iterator<Alumno> al=alumnos.values().iterator();
		while(al.hasNext()) {
			al.next().getDatos();
		}
		 
		//List Iterator
		/*
		ListIterator<Alumno> al=alumnos.listIterator();
		while(al.hasNext()) {
			al.next().getDatos();
		}
		 */

	}

	private static void borrar(Map<String, Alumno> alumnos) {

		System.out.println("Introduce dni: ");
		String auxDni=Util.introducirCadena();
		//For-each
		/*for(Alumno a:alumnos) {
			if(a.getDni().equalsIgnoreCase(x)) {
				alumnos.remove(alumnos.indexOf(a));
				System.out.println("Alumno eliminado");
				break;
			}
		}*/
		//ListIterator
		Alumno auxInfo=alumnos.get(auxDni);
		System.out.println("Quieres borrar a "+auxInfo.getNombre()+"  "+auxInfo.getApellido());
		char opc=Util.respCharList("SN");
		if(opc=='S') {
			alumnos.remove(auxDni);
		}

	}

	private static void modificar(Map<String, Alumno> alumnos) {
		System.out.println("Introduce dni: ");
		String auxDni=Util.introducirCadena();
		Alumno a=alumnos.get(auxDni);
		a.getDatos();
		System.out.println("Queires modificar la clasificacion: ");
		char opc=Util.respCharList("SN");
		if(opc=='S') {
			alumnos.get(auxDni).setClasificacion();
		}




	}

	private static void listar2(Map<String, Alumno> alumnos) {
		Alumno alu[]=new Alumno[alumnos.size()];
		alumnos.values().toArray(alu);
		for(int i=0;i<alumnos.size();i++) {
			for(int j=i+1;j<alumnos.size();j++) {
				if(alu[i].getAnnio()>alu[j].getAnnio()) {
					Alumno aux=alu[i];
					alu[i]=alu[j];
					alu[j]=aux;
				}
			}
		}
		for(Alumno al:alu) {
			al.getDatos();
		}

	}



	private static void nomApe(Map<String, Alumno> alumnos) {
		boolean repe;
		ArrayList <Listado1>li=new ArrayList <Listado1>();
		Iterator alu1=alumnos.values().iterator();
		while(alu1.hasNext()) {
			repe=false;
			Alumno auxComp=(Alumno) alu1.next();
			Iterator alu2=alumnos.values().iterator();
			for(int j=0;j<li.size();j++) {
				if(auxComp.getAnnio()==li.get(j).getAnnio()) {
					repe=true;
				}
			}
			if(!repe) {
				Listado1 aux=new Listado1();
				aux.setAnnio(auxComp.getAnnio());
				Iterator alu=alumnos.values().iterator();
				while(alu.hasNext()) {
					Alumno auxAlu=(Alumno) alu.next();
					if(auxAlu.getAnnio()==aux.getAnnio()) {
						aux.addNombre(auxAlu.getNombre()+" "+auxAlu.getApellido());

					}

				}
				li.add(aux);
			}
		}
		Collections.sort(li);
		for(Listado1 l:li) {
			System.out.println("Año "+l.getAnnio());
			l.muestraNombre();
			System.out.println("Total: "+l.getCont());
		}

	}

	private static void listar9(Map<String, Alumno> alumnos) {
		System.out.println("Introduce un año: ");
		int auxAnnio=Util.leerInt();
		Listado2 li[]=new Listado2[4];
		Iterator alu=alumnos.values().iterator();
		li[0]=new Listado2("Insuficiente");
		li[1]=new Listado2("Bien");
		li[2]=new Listado2("Notable");
		li[3]=new Listado2("Sobresaliente");
		while(alu.hasNext()) {
			Alumno a=(Alumno) alu.next();
			if(a.getAnnio()==auxAnnio) {
				if(a.getClasificacion().equalsIgnoreCase("insuficiente")) {
					li[0].setTipo("Insuficiente");
					li[0].masCont();
					li[0].setEdad(a.getEdad());
				}else if(a.getClasificacion().equalsIgnoreCase("bien")) {
					li[1].setTipo("Bien");
					li[1].masCont();
					li[1].setEdad(a.getEdad());
				}else if(a.getClasificacion().equalsIgnoreCase("notable")) {
					li[2].setTipo("Notable");
					li[2].masCont();
					li[2].setEdad(a.getEdad());
				}else if(a.getClasificacion().equalsIgnoreCase("sobresaliente")) {
					li[3].setTipo("Sobresaliente");
					li[3].masCont();
					li[3].setEdad(a.getEdad());
				}
			}
		}
		for(int i=0;i<4;i++) {
			for(int j=i+1;j<4;j++) {
				if(li[i].getMedia()>li[j].getMedia()) {
					Listado2 aux=li[i];
					li[i]=li[j];
					li[j]=aux;
				}
			}
		}
		System.out.println("Clasificacion     Edad Media");
		for(Listado2 l:li) {
			System.out.println(l.getTipo()+"  "+l.getMedia());
		}

	}

}
