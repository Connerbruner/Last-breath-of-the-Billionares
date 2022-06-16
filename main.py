import discord
import os
from discord.ext import tasks
import multiprocessing

ticks = 0
home = 981204380428673045
client = discord.Client()

def java():
    os.system("chmod +x main.bash")
    os.system("./main.bash")
@client.event
async def on_ready():
    
    print("Bot online (please wait for java to load) ")
    txt.start()
    if __name__ == '__main__':
        #starts Processes java and bot
        p1 = multiprocessing.Process(target=java)
        p1.start()

# 2069 channel 912762550679142442
# bot testing 972268992876150887
@tasks.loop(seconds=1.0)
async def txt():
    f = open("bot.txt", "r")
    msg = f.read()
    f.close()
    if msg == "":
        return
    else:
        channel = client.get_channel(home)
        await channel.send(msg)
        open("bot.txt", "w").close()
@client.event
async def on_message(message):
    if message.content.startswith("!"):
        if "link" in message.content:
         message.channel.send("https://replit.com/@ConnerBrunner/Last-breath-of-the-Billionares")
        if "home" in message.content:
         message.channel.send(home)
#starts Processes java and bot
try:
 r = open("token.txt", "r")
 token = r.read()[1:len(r.read())-1]
 client.run(token)
except:
 print("Please run again (discord API is not happy with repl.it)")
 os.system("kill 1")