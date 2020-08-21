
function fetchIdea() {

    fetch('/ideaPage').then(response => response.json()).then((idea) => {

    console.log(idea);

    const ideaTitleElement = document.getElementById('idea-title');
    ideaTitleElement.innerHTML = '';
    ideaTitleElement.appendChild(createHeadingh1Element(idea.title));  

    const ideaAuthorElement = document.getElementById('author');
    ideaAuthorElement.innerHTML = '';
    ideaAuthorElement.appendChild(createHeadingh2Element(idea.authorID));  

    const ideaDescriptionElement = document.getElementById('idea-description');
    ideaTitleElement.innerHTML = '';
    ideaDescriptionElement.appendChild(createParaElement(idea.description));  


    const ideaImageElement = document.getElementById('image');
    var img  = document.createElement('img');
    img.src = idea.imageURL;
    ideaImageElement.appendChild(img);

    const ideaCategoriesElement = document.getElementById('idea-categories');
    ideaCategoriesElement.innerHTML = '';
    ideaCategoriesElement.appendChild(createListElement(idea.category));  		

    
  });
}

function createParaElement(text) {
  const ParaElement = document.createElement('p');
  ParaElement.innerText = text;
  return ParaElement;
}

function createHeadingh1Element(text) {
  const Headingh1Element = document.createElement('H1');
  Headingh1Element.innerText = text;
  return Headingh1Element;
}

function createHeadingh2Element(text) {
  const Headingh2Element = document.createElement('H2');
  Headingh2Element.innerText = text;
  return Headingh2Element;
}

function createListElement(text) {
  const ParaElement = document.createElement('li');
  ParaElement.innerText = text;
  return ListElement;
}