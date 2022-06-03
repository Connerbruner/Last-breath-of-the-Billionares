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
@tasks.loop(seconds=1)
async def txt():
    global ticks
    ticks += 1
    channel = client.get_channel(home)
    f = open("bot.txt", "r")
    msg = f.read()
    f.close()
    if msg == "":
        return
    else:
        await channel.send(msg)
        open("bot.txt", "w").close()
        open("botcmd.txt", "w").close()


@client.event
async def on_message(message):
    if message.author == client.user:
        return
    elif message.author.id == 563434444321587202 and "cre‌dits to" in message.content and "971959085723430982" in message.content:
        if "892485922363416717" in message.content or "536260753506697227" in message.content or "609334195285655553" in message.content or "429465017490866177" in message.content:
            await message.channel.send("mods can not use this command")
        else:
            f = open("botcmd.txt", "w")
            f.write(message.content)

    elif message.content.startswith("!"):
        if "tick" in message.content:
            await message.channel.send(ticks)
        if "user" in message.content:
            await message.channel.send(client.user)
        if "base" in message.content:
            await message.channel.send(client.get_channel(home))
        if "link" in message.content:
            await message.channel.send(
                "https://replit.com/@ConnerBrunner/Last-breath-of-the-Billionares#main.py"
            )
        if "help" in message.content:
            await message.channel.send(
                "CURRENT COMMANDS:  elp, link , base , user , tick")


#starts Processes java and bot
token = 0
client.run(token)
