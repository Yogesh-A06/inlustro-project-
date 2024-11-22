import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javax.mail.*;
// import javax.mail.Message;
import javax.mail.internet.*;
import java.util.Properties;

public class inluemail extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    PrintWriter out = res.getWriter();
    res.setContentType("text/html");
    
    try {
        String s1 = req.getParameter("email");
        String s2 = req.getParameter("sub");
        String s3 = req.getParameter("message");
        
        // Debug statements
        out.println("<h3>Debug Information:</h3>");
        out.println("<p>Email: " + s1 + "</p>");
        out.println("<p>Subject: " + s2 + "</p>");
        out.println("<p>Message: " + s3 + "</p>");
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");  // Changed back to 587
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Added this
        
        out.println("<p>Properties set</p>");
        
        Session ses = Session.getInstance(prop, new Authenticator() {
            @Override  // Added this
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lokeshanbuselvan@gmail.com", "zvnp gfoj vlpz tnwl");
            }
        });
        
        ses.setDebug(true);  // This will print debug info to console
        out.println("<p>Session created</p>");  
       


       Message message = new MimeMessage(ses);
        message.setFrom(new InternetAddress());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(s1));
        message.setSubject(s2);
        message.setContent(s3);
        Transport.send(message);
        out.println("<h3>Mail sent successfully!</h3>");
    }
    catch(MessagingException e) {
        out.println("<h3>Mail Error:</h3>");
        out.println("<p>Error type: " + e.getClass().getName() + "</p>");
        out.println("<p>Error message: " + e.getMessage() + "</p>");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        out.println("<pre>" + sw.toString() + "</pre>");
    }
    catch(Exception e) {
        out.println("<h3>General Error:</h3>");
        out.println("<p>Error type: " + e.getClass().getName() + "</p>");
        out.println("<p>Error message: " + e.getMessage() + "</p>");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        out.println("<pre>" + sw.toString() + "</pre>");
    }
}
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        doGet(req,res);
    }

}