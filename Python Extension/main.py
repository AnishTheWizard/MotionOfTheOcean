from scipy import interpolate
import numpy as np
import matplotlib.pyplot as pp

x = np.array([i for i in range(1, 6)])
y = np.array([2, 3, 4, 5, 1])

f = interpolate.interp1d(x, y, kind='cubic', fill_value='extrapolate')
xnew = np.arange(1, 5, 0.1)
ynew = f(xnew)
print(f)
print(ynew)
pp.plot(x, y, xnew, ynew)

pp.show()