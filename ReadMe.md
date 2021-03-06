# Starcrash

Improvements (hopefully) to the [BIFF](http://bitwisebooks.com/program-adventure-games-with-the-biff-framework/) (*Bitwise Interactive Fiction Framework*) by  [Huw Collingbourne](http://bitwisebooks.com/author/huw/).

I am using his book [The Little Book of Adventure Game Programming](http://bitwisebooks.com/little-book-of-adventure-game-programming-in-java/), to learn Java programming concepts.

Huw has a [YouTube playlist](https://www.youtube.com/playlist?list=PLZHx5heVfgEvT5BD8TgLmGrr-V64pX7MD) in which he goes over some of the basic structures he created in the book.

The original version of Starcrash is included in the [source code downloads](http://bitwisebooks.com/source-code-downloads/) for this book.

![JavaAdventure_Medium](img/JavaAdventure_Medium.png)

This personal repository may be used with the restrictions on BIFF as stated below:

> "Early releases will always be substantially incomplete. However, if you  want to deepen your understanding of game programming, this is a great  opportunity for you to try to modify the latest release by adding on  additional features. You are free to modify BIFF as required as long as  you leave my copyright notice in the comments of the source code."

This transcript illustrates some of the improvements to be made.

```
game.Starcrash.main({ });
You find yourself on the bridge of the HMS Starcrash,
an elite-class starship of Her Majesty's Royal Fleet.
What do you want to do?
(Enter q to quit)
look
This is an ultra-modern starship bridge.
A huge viewscreen dominating one wall shows space in this quadrant.
There is a console on a desk with a slot beneath it.
x console
Invalid command 'x console'
x not understood
examine console
Invalid command 'examine console'
examine not understood
look at console
It is a display console built into the desk.
It is displaying this message:
Place activation card into slot to engage faster-than-light drive.
quit
quit (not implemented)
q
ok
```

...And here is the start of a great adventure to examine...

```
You find yourself on the bridge of the HMS Starcrash,
an elite-class starship of Her Majesty's Royal Fleet.
What do you want to do?
> look
This is an ultra-modern starship bridge.
A huge viewscreen dominating one wall shows space in this quadrant.
There is a console on a desk with a slot beneath it.
> x console
It is a display console built into the desk.
It is displaying this message:
"Place activation card into slot to engage faster-than-light drive.".
> engage
OK, Jean-Luc. We'll engage. Really?
> examine
examine what?
OK, on further examination you find you aren't examining anything.
> examine console
It is a display console built into the desk.
It is displaying this message:
"Place activation card into slot to engage faster-than-light drive.".
> quit
OK, I'm sure you have better things to do with your time.
I'm not sure what, but whatever.

Process finished with exit code 0
```

