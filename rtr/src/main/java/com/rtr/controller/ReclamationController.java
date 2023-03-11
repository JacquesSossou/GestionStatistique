package com.rtr.controller;

import com.rtr.model.*;
import com.rtr.service.EtatService;
import com.rtr.service.ReclamationEtatService;
import com.rtr.service.ReclamationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping(value = {"/api/", "/api/auth"})
@RequiredArgsConstructor
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @Autowired
    private EtatService etatService;

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private ReclamationEtatService reclamationEtatService;
    public static String generateString(String gene, int leng) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < leng; i++) {
            sb.append(gene.charAt(random.nextInt(gene
                    .length())));
        }
        return sb.toString();
    }
    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jacksossou9@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @GetMapping("/consulterEtat/{codeConsultation}/{referenceDossier}")
    public Reclamation getReclamationPlaignantEtat(@PathVariable("codeConsultation") final String code,@PathVariable("referenceDossier") final String dossier){
        Reclamation reclamationNum = reclamationService.findByDossier(dossier);
        Reclamation reclamationCode = reclamationService.findByCode(code);
        if (reclamationNum.equals(reclamationCode)){
            return reclamationCode;
        }
        return  null;
    }

    @PostMapping("/reclamation")
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {
        String nom = reclamation.getNom().toString().substring(0,4);
        String prenoms = reclamation.getPrenoms().toString().substring(0,4);
        String numConsult = nom + prenoms + generateString("123457",4) + reclamation.getEmail().substring(0,6);
        reclamation.setCodeConsultation(numConsult);
        String subject = "Code pour consulter le statut de sa réclamation";
        String content = "Bonjour M/Mm,"+nom+" "+prenoms
                + " " + "<br>"+
                " Vous avez demandé à reinitialiser votre mot de passe. Voici votre code :"
                + numConsult;
        sendSimpleMessage(reclamation.getEmail(),subject,content);
        reclamation.setCodeConsultation(numConsult);
        Reclamation reclamationSaved = reclamationService.saveReclamation(reclamation);
        Etat etat = etatService.getEtatByLabel("envoyer").get();
        ReclamationEtatKey reclamationEtatKey = new ReclamationEtatKey();
        reclamationEtatKey.setReclamationId(reclamationSaved.getId());
        reclamationEtatKey.setEtatId(etat.getId());

        ReclamationEtat reclamationEtat = new ReclamationEtat();
        reclamationEtat.setReclamation(reclamationSaved);
        reclamationEtat.setEtat(etat);
        reclamationEtat.setDatetime(LocalDateTime.now());
        reclamationEtat.setId(reclamationEtatKey);
        System.out.println("The id is there " +reclamationEtat);
        ReclamationEtat re = reclamationEtatService.saveReclamationEtat(reclamationEtat);

        System.out.println("The id is there " +re);

        //reclamationSaved.getReclamationEtats().add(reclamationEtat);
        //etat.getReclamationEtats().add(reclamationEtat);

        return reclamationSaved;
    }

    @GetMapping("/reclamation/{id}")
    public Reclamation getReclamation(@PathVariable("id") final Long id) {
        Optional<Reclamation> reclamation = reclamationService.getReclamation(id);
        if(reclamation.isPresent()) {
            return reclamation.get();
        } else {
            return null;
        }
    }



    @GetMapping("reclamations")
    public Iterable<Reclamation> getReclamations() {
        return reclamationService.getReclamations();
    }

    @PutMapping("/reclamation/{id}")
    public Reclamation updateReclamation(@PathVariable("id") final Long id, @RequestBody Reclamation reclamation) {
        Optional<Reclamation> r = reclamationService.getReclamation(id);
        if(r.isPresent()) {
            Reclamation currentReclamation = r.get();
            String nom = reclamation.getNom();
            if(nom != null) {
                currentReclamation.setNom(nom);
            }

            String prenoms = reclamation.getPrenoms();
            if(prenoms != null) {
                currentReclamation.setPrenoms(prenoms);
            }

            String telephone = reclamation.getTelephone();
            if(telephone != null) {
                currentReclamation.setTelephone(telephone);
            }

            String email = reclamation.getEmail();
            if(email != null) {
                currentReclamation.setEmail(email);
            }

            String referenceDossier = reclamation.getReferenceDossier();
            if(referenceDossier != null) {
                currentReclamation.setReferenceDossier(referenceDossier);
            }

            String motif = reclamation.getMotif();
            if(motif != null) {
                currentReclamation.setMotif(motif);
            }

            double montant = reclamation.getMontant();
            if(montant > 0) {
                currentReclamation.setMontant(montant);
            }

            Structure structure = reclamation.getStructure();
            if(structure != null) {
                currentReclamation.setStructure(structure);
            }

            Prestation prestation = reclamation.getPrestation();
            if(prestation != null) {
                currentReclamation.setPrestation(prestation);
            }

            Set<ReclamationEtat> reclammationEtats = reclamation.getReclamationEtats();
            if(reclammationEtats != null) {
                currentReclamation.setReclamationEtats(reclammationEtats);
            }

            Set<Transaction> transactions = reclamation.getTransactions();
            if(transactions != null) {
                currentReclamation.setTransactions(transactions);
            }


            reclamationService.saveReclamation(currentReclamation);
            return currentReclamation;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/reclamation/{id}")
    public void deleteReclamation(@PathVariable ("id") final Long id) {
        reclamationService.deleteReclamation(id);
    }
}
