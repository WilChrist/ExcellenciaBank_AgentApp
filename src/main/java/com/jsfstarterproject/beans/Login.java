package com.jsfstarterproject.beans;

import com.jsfstarterproject.dao.LoginDao;
import com.jsfstarterproject.models.Account;
import com.jsfstarterproject.models.Agent;
import com.jsfstarterproject.models.Client;
import com.jsfstarterproject.util.SessionUtils;
import okhttp3.Response;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    @ManagedProperty(name = "agent", value = "#{agent}")
    private Agent agent;
    @ManagedProperty(name = "client", value = "#{client}")
    private Client client;
    @ManagedProperty(name = "isWellUpdated", value = "#{isWellUpdated}")
    private boolean isWellUpdated;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isIsWellUpdated() {
        return isWellUpdated;
    }

    public void setIsWellUpdated(boolean isWellUpdated) {
        this.isWellUpdated = isWellUpdated;
    }


    @PostConstruct
    private void init(){
        agent = new Agent();
        client = new Client();
    }
    //validate login
    public String validateLoginPassword() throws IOException {
        Response response = LoginDao.validate(agent.getLogin(), agent.getPassword());
        if (response.header("Authorization") != null) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("token", response.header("Authorization"));
            //session.setAttribute("myagent", LoginDao.getAgent(agent.getEmail(), agent.getPassword()));
            agent = LoginDao.getAgent(response);
            response.close();
            if (agent.getLogin() == null) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Login and Passowrd (of Agent)",
                                "Please enter correct Login and Password"));
                return "login";
            }
            return "agent";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Login and Passowrd",
                            "Please enter correct Login and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    public String showUpdateClientForm() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String clientpk = params.get("clientpk");
        //System.out.println(clientpk);
        for (Client c : agent.getManagedClients()
                ) {
            if (c.getPk().toString().equals(clientpk)) {
                //System.out.println( "trouvé**********************");
                client = c;
                break;
            }
        }
        return "updateclient";
    }

    public String updateClient() throws IOException {
        System.out.println("updated**********************");


        Response response = LoginDao.updateClientOnApi(client);
        isWellUpdated = response.isSuccessful();
        client = new Client();
        return "agent";
    }

    public String addClient() throws IOException {
        System.out.println("added**********************");
        client.setAccount(new Account());
        client.setAgency(agent.getAgency());
        client.setAgent(agent);
        Response response = LoginDao.postClientOnApi(client);
        if (response.isSuccessful()) {
            isWellUpdated = true;
            client = LoginDao.getClient(response);
            agent.getManagedClients().add(client);
        } else
            isWellUpdated = false;
        client = new Client();
        return "agent";
    }

    public String showDeleteClientForm() {
        return "agent";
    }
}
