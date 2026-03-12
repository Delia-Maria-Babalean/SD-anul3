<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizare Student</title>
</head>
<body>

<form action="./update-student" method="POST">
    <fieldset>
        <legend>Cautare student existent</legend>

        Nume:
        <input type="text" name="numeVechi" required>
        <br/>

        Prenume:
        <input type="text" name="prenumeVechi" required>
        <br/>

        Varsta:
        <input type="number" name="varstaVechi" required>
    </fieldset>

    <br/>

    <fieldset>
        <legend>Date noi</legend>

        Nume Nou:
        <input type="text" name="numeNou" required>
        <br/>

        Prenume Nou:
        <input type="text" name="prenumeNou" required>
        <br/>

        Varsta Noua:
        <input type="number" name="varstaNou" required>
    </fieldset>

    <br/>

    <input type="submit" value="Actualizeaza">
    <br/><br/>
    <a href='./'>Inapoi la meniul principal</a>
</form>

</body>
</html>