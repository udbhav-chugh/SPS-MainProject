// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// <div style="display:inline-block;">
//     <div class="card mb-3 text-center" style="width: 22rem; margin-right: 1.5em;">
//         <img class="card-img-top" src="..." alt="Card image cap">
//         <div class="card-body">
//             <h4 class="card-title">Product 1</h4>
//             <b  class="card-text">Locations:</b>
//             <br/>
//             <br/>
//                 <a href="/dashboard.html" class="btn btn-dark">Update Statistics</a>

//                 <!-- <a href="{% url 'main:update_statistics' disaster.id %}" class="btn btn-dark">Update Statistics</a> -->
//             <br>
//             <hr>
//         </div>
//     </div>
// </div>

function getcardTitle(idea){
    var cardTitle = document.createElement("h4");
    cardTitle.className = "card-title";
    var titleText = document.createTextNode(idea.title);
    cardTitle.appendChild(titleText);
    return cardTitle;
}

function getCardDescription(idea){
    var cardDescription = document.createElement("b");
    cardDescription.className = "card-text";
    var descriptionText = document.createTextNode(idea.description);
    cardDescription.appendChild(descriptionText);
    return cardDescription;
}

function getStatsLink(idea){
    var statsLink = document.createElement("a");
    statsLink.className = "btn btn-dark";
    var link = document.createTextNode("Analyse Statistics");
    statsLink.appendChild(link);
    statsLink.href = "/statistics.html?productID="+idea.productID;
    return statsLink;
}

function getCardBody(idea){
    var cardBody = document.createElement("div");
    cardBody.className = "card-body";
    
    var cardTitle = getcardTitle(idea);
    var cardDescription = getCardDescription(idea);
    var lineBreak = document.createElement("br");
    var statsLink = getStatsLink(idea);
    var lineBreak2 = document.createElement("br");

    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardDescription);
    cardBody.appendChild(lineBreak);
    cardBody.appendChild(statsLink);
    cardBody.appendChild(lineBreak2);
    return cardBody;
}

function getIdeaCard(idea){
    var ideaCard = document.createElement("div");
    ideaCard.className = "card mb-3 text-center";
    ideaCard.style.width = "22rem";
    ideaCard.style.margin = "1.5em";
    if(idea.imageUrl){
        var img = document.createElement("img"); 
        img.className = "card-img-top";
        img.src =  idea.imageUrl;
        img.width = "400"
        img.height = "300"
        ideaCard.appendChild(img);
    }
    ideaCard.appendChild(getCardBody(idea));
    return ideaCard;
}

function getIdeaDiv(idea){
    var ideaDiv = document.createElement("div");
    ideaDiv.style.display = "inline-block";
    ideaDiv.appendChild(getIdeaCard(idea));    
    return ideaDiv;
}

function addIdeasToDashboard() {

  fetch('/product-idea').then(response => response.json()).then((ideas) => {

    var ideasContainer = document.getElementById('ideas-container');
    for(var i = 0; i < ideas.length; i++){
        var ideaDiv = getIdeaDiv(ideas[i]);
        ideasContainer.appendChild(ideaDiv);
    }

  });
}

function fetchBlobstoreUrlAndShowForm() {
  fetch('/blobstore-profile-upload-url')
      .then((response) => {
        return response.text();
      })
      .then((uploadUrl) => {
        const messageForm = document.getElementById('updateinfo-form');
        messageForm.action = uploadUrl;
      });
}

function fetchBlobstoreUrlAndShowIdeaForm() {
  fetch('/blobstore-idea-upload-url')
      .then((response) => {
        return response.text();
      })
      .then((uploadUrl) => {
        const messageForm = document.getElementById('postidea-form');
        messageForm.action = uploadUrl;
      });
}

$(function(){
    $('select').selectpicker();
});