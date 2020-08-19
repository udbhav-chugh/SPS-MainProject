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

function getCommentPara(comment){
    var commentPara = document.createElement("p");
    var commentText = document.createTextNode(comment.comment);
    commentPara.appendChild(commentText);
    return commentPara;
}

function getCommentFooter(comment){
    var authorText = document.createTextNode(comment.author + ", ");
    var organizationText = document.createTextNode(comment.organization);
    var italicsOrganizationText = document.createElement("i");
    italicsOrganizationText.appendChild(organizationText);

    var commentFooter = document.createElement("footer");
    commentFooter.className = "blockquote-footer";
    commentFooter.appendChild(authorText);
    commentFooter.appendChild(italicsOrganizationText);
    return commentFooter;
}

function getCommentData(comment){
    var blockQuote = document.createElement("blockquote");
    blockQuote.className = "blockquote mb-0";
    blockQuote.appendChild(getCommentPara(comment));
    if(comment.imageUrl){
        var img = document.createElement("img"); 
        img.src =  comment.imageUrl;
        blockQuote.appendChild(img);
    }
    blockQuote.appendChild(getCommentFooter(comment));
    return blockQuote;
}

function getCommentDiv(comment){
    var commentDiv = document.createElement("div");
    commentDiv.className = "commentbackground";
    commentDiv.appendChild(getCommentData(comment));    
    return commentDiv;
}

function addCommentsToPortfolio() {

  fetch('/data-get').then(response => response.json()).then((comments) => {

    var commentsContainer = document.getElementById('comments-container');
    for(var i = 0; i < comments.length; i++){
        var commentDiv = getCommentDiv(comments[i]);
        var lineBreak = document.createElement("br");

        commentsContainer.appendChild(commentDiv);
        commentsContainer.appendChild(lineBreak);
    }

  });
}

function fetchBlobstoreUrlAndShowForm() {
    console.log("hello 1")
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
    console.log("hello hello")
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