package es.uc3m.tiw.control.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.modelo.Usuario;
import es.uc3m.tiw.modelo.daos.UsuarioDAO;
import es.uc3m.tiw.modelo.daos.UsuarioDAOImpl;

@WebServlet("/administrador")
public class Administrador extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String PRODUCTOS = "/Producto_admin.jsp";
	private static final String USUARIOS = "/PaginaPrincipal_admin.jsp";

	private String PAGINA = "";

	@PersistenceContext(unitName = "wallapoptiw")
	private EntityManager em;

	@Resource
	private UserTransaction ut;
	private ServletConfig config;
	private UsuarioDAO usuarioDAO;
	
	
	public void init(ServletConfig config) throws ServletException{

		this.config = config;
		usuarioDAO = new UsuarioDAOImpl (em,ut);
	}


	public void destroy() {
		usuarioDAO = null;

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String accion = request.getParameter("accion");
		
		if(accion.equals("eliminar_usuario")){
			
			
			try{
				Usuario user = null;
				user = usuarioDAO.recuperarUnUsuarioPorClave(id);
				usuarioDAO.borrarUsuario(user);
				PAGINA=USUARIOS;
			}
			catch (Exception e){
				PAGINA=USUARIOS;
				e.printStackTrace();
				//mensaje no se ha podido eliminar
				
			}
	
		}
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/*
		HttpSession sesion = (HttpSession) request.getSession(false);
		Usuario user = (Usuario) sesion.getAttribute("usuario_sesion");
		
		
		
		//if(accion.equals("Editar_usuario")){
			
			String cont_actual=request.getParameter("ContrasenyaActual");
			String cont_nueva=request.getParameter("NuevaContrasenya");
			String verif_cont=request.getParameter("VerificarContrasenya");							
			
			if(cont_actual.equals("")||cont_nueva.equals("")||verif_cont.equals("")){
			//	PAGINA=MICONTRASENYA_JSP;
				//No se ha podido cambiar la contraseña
			}
			else{
				//Contraseña actual es correcta y coinciden las contraseñas nuevas
				if((user.getPassword().equals(cont_actual))&&(cont_nueva.equals(verif_cont))){
					user.setPassword(cont_nueva);
					user.setPassVerif(cont_nueva);
				}
				
				try{
					user=usuarioDAO.actualizarUsuario(user);
					sesion.setAttribute("usuario_sesion", user);
				//	PAGINA=MICONTRASENYA_JSP;
					//Mensaje si se ha podido editar
					System.out.println("SI");
				}
				catch (Exception e){
				//	PAGINA=MICONTRASENYA_JSP;
					//Mensaje no se ha podido editar
					System.out.println("NO");
					
				}
			}
			
		//}
		
		config.getServletContext().getRequestDispatcher(PAGINA).forward(request, response);	
	*/
	}
	

}