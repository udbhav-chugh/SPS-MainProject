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