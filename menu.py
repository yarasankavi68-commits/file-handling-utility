l={'eat','ate','tee','tea','taa'}
out=[]
for i in l:
    temp={}
    for j in i:
        temp[j]=i.count(j)
    out+=[temp]
print(out)
n=0
for i in range(0,len(out)):
    for j in range(i+1,len(out)):
               if out[i]==out[j]:
                 n+=1
print(n)