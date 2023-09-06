# github-api-consumer
Application to get information about github user repositories. 
Endpoint to get repositories: 
localhost:8080/{username}/repositories
username - login from github
 
Result returns a list of repositories consisting of:
name - name of repository 
owner - owner of repository 
list of branches - list of objects that includes branch name and sha 
