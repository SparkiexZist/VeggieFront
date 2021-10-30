function incrementValue(vegetable)
{
    var value = parseInt(document.getElementById(vegetable.id+'increase').value);
    if(value >= 0 && value < vegetable.amount)
        value = value+1;
    var result =document.getElementById(vegetable.id+"increase").value = value;
    var result = document.getElementById(vegetable.id+"increase2").value = value;


}
function decrementValue(vegetable)
{
    var value = parseInt(document.getElementById(vegetable.id+'increase').value);
    if(value >= 1)
        value = value-1;
    var result =document.getElementById(vegetable.id+"increase").value = value;
    var result = document.getElementById(vegetable.id+"increase2").value = value;
}

function createId(){
    var d = new Date();
    var n = d.toString();
    document.getElementById(history_idInput).value = n;
}
// JavaScript code
function search_animal()
{
    let input = document.getElementById('searchbar').value
    input=input.toLowerCase();
    let x = document.getElementsByClassName('ord');

    for (i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input))
        {
            x[i].style.display="none";
        }
        else {
            x[i].style.display="block";
        }
    }
}

