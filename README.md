# SpringBootSecurityLearn
#learn springboot security step by step
#THIS EXAMPLE USED MYBATIS AS DB
#SPRING BOOT SECURITY IS VERY IMPORTANT.
#PROBABLY YOU WANT TO USE JWT OR OAUTH2 TO PROTECT YOUR WEBSITE, BUT YOU HAVE TO FULLY UNDERSTAND SPRING SECURITY.
#SPRING SECURITY ARE ACTUALLY SOME FILTERS AND INTERCEPTERS
#SecurityContextHolder(recieve the authentication instance from manager)
-->AuthManager(it need auth instance from provider) 
--> AuthProvider (get auth from Service)
--> AuthService (your logic that use the info of database to match the input)
#IN THE END, SecurityContextHolder GET THE AUTHENTICATION.
#THIS IS A VERY SIMPLE SPRING SECURITY EXAMLPLE.
#I USE MYBATIS TO STORE USER INFO, AND authenticate THE USER INFO IN SERVICE CLASS.
#SOME TIPS:
#1)USER CLASS MUST implements UserDetails CLASS
#2)ROLE IN SQL MUST STARTED WITH "ROLE_". 
YOU CAN GO THROUGH SPRING SECURITY CODES. THE method hasRole() return "hasRole('ROLE_"+role+"')"
#3)SERVICE IS AUTO REGIGSTERED.

#I WILL POST MORE EXAMPLES IN THE FUTURE.
