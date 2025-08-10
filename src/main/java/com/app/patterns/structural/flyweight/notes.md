In game development, especially in scenarios like shooting games where numerous
identical bullets are fired, memory and performance can quickly become an issue if
each badBullet object stores redundant data. Using the **Flyweight Pattern**, we can
reduce memory overhead by sharing intrinsic properties of bullets (like appearance)
while maintaining unique extrinsic properties (like position and velocity)