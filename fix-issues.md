
# Fix for google-services.json

We can store the content of google-services.json in Environment variable (aka Secrets in github). Actually github uses linux based cli so we have to execute some command on the cli using github actions.
There will be two steps:

* Firstly create the google-services.json file in base64:
    - name: Create file
      run: cat /home/runner/work/<Project-Name>/<Project-Name>/app/google-services.json | base64

* Then put data in the file (basically this fetch data from github secrets and put the data in json file before building the application)
    - name: Putting data
      env:
      DATA: ${{ secrets.GOOGLE_SERVICES_JSON }}
      run: echo $DATA > /home/runner/work/<Project-Name>/<Project-Name>/app/google-services.json

* Then define the content of google-services.json file in the github secrets via: Setting > Secrets > New Repository Secret using name GOOGLE_SERVICES_JSON

Both of these commands should be placed before the gradle build command in gradle.yml
By doing this your google-services.json file will be created and has data also so the app will build successfully.

source: https://stackoverflow.com/questions/70568653/google-services-json-file-for-github-actions
