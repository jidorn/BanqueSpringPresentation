package fr.afcepf.al26.banquespring.presentation.controller;

import fr.afcepf.al26.banquespring.business.api.IBusinessConnection;
import fr.afcepf.al26.banquespring.business.impl.BusinessConnection;
import fr.afcepf.al26.banquespring.entity.Client;
import fr.afcepf.al26.banquespring.entity.Utilisateur;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Stagiaire on 15/01/2016.
 */

@ManagedBean (name = "mbConnexion")
@SessionScoped
public class ConnectionMB {
    private Utilisateur utilisateur;
    private String mail;
    private String mdp;

    public String connecter(){
        IBusinessConnection connection = new BusinessConnection();
        utilisateur = connection.seConnecter(mail,mdp);
        if (utilisateur != null)
            return "/accueilUser.xhtml?faces-redirect=true";
        else {
            assert false;
            if (utilisateur.getClass() == Client.class)
                return "/accueilUser.xhtml?faces-redirect=true";
            else
                return "/accueilConseiller.xhtml?faces-redirect=true";
        }
    }

    public String seDeconnecter(){
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        return "";
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
