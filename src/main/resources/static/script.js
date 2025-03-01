function showForm() {
    document.querySelector('.background').style.display = 'none';
    document.querySelector('form').style.display = 'block';
}

let matriculeRecherche = "";

function showInfoDialog() {
    document.querySelector('#infoDialog').style.display = 'flex';
}

function closeInfoDialog() {
    matriculeRecherche = document.getElementById("matriculeInput").value;
    document.querySelector('#infoDialog').style.display = 'none';
    if (matriculeRecherche) {
        window.location.href = '/etudiants/' + matriculeRecherche + '/pdfs';
    }
}

function closeInfoDialogCarte() {
    matriculeRecherche = document.getElementById("matriculeInput").value;
    document.querySelector('#infoDialog').style.display = 'none';
    if (matriculeRecherche) {
        window.location.href = '/etudiants-carte/' + matriculeRecherche + '/pdfs';
    }
}

function handleInfoChange() {
    const select = document.getElementById('infoSelect');
    const selectedOption = select.value;

    if (selectedOption === "pdf_scolarite") {
        showInfoDialog(); // Ouvre la boîte de dialogue pour le PDF de scolarité
    } else if (selectedOption === "carte_etudiant") {
        showInfoDialog(); // Ouvre la boîte de dialogue pour la carte étudiant
        document.querySelector('#infoDialog button[onclick="closeInfoDialog()"]').onclick = closeInfoDialogCarte; // Change l'action du bouton
    }
}

function AfficheAccueil() {
    document.querySelector('.background').style.display = 'flex';
    document.querySelector('form').style.display = 'none';
    document.querySelector('.content').style.display = 'block';
}

function nextStep(step) {
    const currentStep = document.querySelector('.step.active');
    currentStep.classList.remove('active');
    const nextStep = document.querySelectorAll('.step')[step];
    nextStep.classList.add('active');
}