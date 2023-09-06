# github-api-consumer
Application to get information about github user repositories. 
### Endpoint to get repositories: 
1. localhost:8080/{username}/repositories
2. username - login from github
 
### Result returns a list of repositories consisting of:
 1. name - name of repository 
 2. owner - owner of repository 
 3. list of branches - list of objects that includes branch name and last commit sha 
