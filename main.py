import discord
import os
from discord.ext import tasks
import multiprocessing 

ticks=0
status="java not running"
home=972268992876150887

client = discord.Client()
def java():
    status="java Running"
    os.system("chmod +x main.bash")
    os.system("./main.bash")
    
@client.event
async def on_ready():
 print("Bot is online")
 txt.start()
 if __name__ == '__main__':
 #starts Processes java and bot
  p1 = multiprocessing.Process(target=java)
  p1.start()

# 2069 channel 912762550679142442
# bot testing 972268992876150887
@tasks.loop(seconds=1)
async def txt():
  global ticks
  ticks+=1
  channel = client.get_channel(972268992876150887)
  f = open("bot.txt", "r")
  msg = f.read()
  f.close()
  if msg=="":
      return
  else:
   await channel.send(msg)
   open("bot.txt", "w").close()

@client.event
async def on_message(message):
    if message.author == client.user:
        return
    elif "!" in message.content:
        if "tick" in message.content:
            await  message.channel.send(ticks)
        elif "java" in message.content:
            await  message.channel.send(status)
        elif "home" in message.content:
            await  message.channel.send(home)
        else:
            await  message.channel.send("Current commands: tick , java , home prefix: !")
        
     
        

#starts Processes java and bot
token = os.environ.get("TOKEN") 
client.run(token)
 