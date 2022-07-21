from string import ascii_lowercase

with open('cipher_text.txt', 'r') as f:
    data = f.read()

unique = ''.join(list(sorted(set(data))))
out = ''

for x in data:
    out += ascii_lowercase[unique.index(x)]

start = 'the date was august world war ii had been raging for two years france had fallen the battle of britain had been fought and the soviet union had just been invaded by nazi germany the bombing of pearl harbor was four months in the future but on that day with europe in flames and the evil shadow of adolf hitler apparently falling over all the world what was chiefly on my mind was a meeting toward which i was hastening i was years old a graduate student in chemistry at columbia university and i had been writing science fiction professionally for three years in that time i had sold five stories to john campbell editor of astounding and the fifth story nightfall was about to appear in the september issue of the magazine i had an appointment to see mr campbell to tell him the plot of a new story i was planning to write and the catch was that i had no plot in mind not the trace of one i therefore tried a device i sometimes use i opened a book at random and set up free association beginning with whatever i first saw the book i had with me was a collection of the gilbert and sullivan plays i happened to open it to the picture of the fairy queen of lolan the throwing herself at the feet of private willis i thought of soldiers of military empires of the roman empire of a galactic empire aha why shouldnt i write of the fall of the galactic empire and of the return of feudalism written from the viewpoint of some one in the secure days of the second galactic empire after all i had read gibbons decline and fall of the roman empire not once but twice i was bubbling over by the time i got to campbells and my enthusiasm must have been catching for campbell blazed up as i had never seen him do in the course of an hour we built up the notion of a vast series of connected stories that were to deal in intricate detail with the thousand year period between the first and second galactic empires this was to be illuminated by the science of psycho history which campbell and i thrashed out between us on august therefore i began the story of that interregnum and called it foundation in it i described how the psycho historian hari seldon established a pair of foundations at opposite ends of the universe under such circumstances as to make sure that the forces of history would bring about the second empire after one thousand years instead of the thirty thousand that would be required otherwise the story was submitted on september and to make sure that campbell really meant what he said about a series i ended foundation on a cliffhanger thus it seemed to me he would be forced to buy a second story however when i started the second story on october i found that i had outsmarted myself i quickly wrote myself into an impasse and the foundation series would have died an ignominious death had i not had a conversation with fred pohl on november on the brooklyn bridge as it happened i dont remember what fred actually said but whatever it was it pulled me out of the hole foundation appeared in the may issue of astounding and the succeeding story bridle and saddle in the june issue after that there was only the routine trouble of writing the stories through the remainder of the decade john campbell kept my nose to the grindstone and made sure he got additional foundation stories the big and the little was in the august astounding the wedge in the october issue and dead hand in the april issue these stories were written while i was working at the navy yard in philadelphia on january i began the mule my personal favorite among the foundation stories and the longest yet for it was words it was printed as a two part serial the very first serial i was ever responsible for in the november and december issues by the time the second part appeared i was in the army after i got out of the army i wrote now you see it which appeared in the january issue by this time though i had grown tired of the foundation stories so i tried to end them by setting up and solving the mystery of the location of the second foundation campbell would have none of that however he forced me to change the ending and made me promise i would do one more foundation story well campbell was the kind of editor who could not be denied so i wrote one more foundation story vowing to myself that it would be the last i called it and now you dont and it appeared as a three part serial in the november december and january issues of astounding by then i was on the biochemistry faculty of boston university school of medicine my first book had just been published and i was determined to move on to new things i had spent eight years on the foundation written nine stories with a total of about words my total earnings for the series came to and that seemed enough the foundation was over and done with as far as i was concerned in however hardcover science fiction was just coming into existence i had no objection to earning a little more money by having the foundation series reprinted in book form i offered the series to double day which had already published a science fiction novel by me and which had contracted for another and to little brown but both rejected it in that year though a small publishing firm gnome press was beginning to be active and it was prepared to do the foundation series as three books the publisher of gnome felt however that the series began too abruptly he persuaded me to write a small foundation story one that would serve as an introductory section to the first book so that the first part of the foundation series was the last written in the gnome press edition of foundation was published containing the introduction and the first four stories of the series in foundation and empire appeared with the fifth and sixth stories and in second foundation appeared with the seventh and eighth stories the three books together came to be called the foundation trilogy the mere fact of the existence of the trilogy pleased me but gnome press did not have the financial clout or the publishing know how to get the books distributed properly so that few copies were sold and fewer still paid me royalties nowadays copies of first editions of those gnome press books sell at a copy and up but i still get no royalties from the mace books did put out paperback editions of foundation and of foundation and empire but they changed the titles and used cut versions any money that was involved was paid to gnome press and i didnt see much of that in the first decade of the existence of the foundation trilogy it may have earned something like total and yet there was some foreign interest in early timothy seldes who was then my editor at doubleday told me that doubleday had received a request for the portuguese rights for the foundation series and since they werent doubleday book she was passing them on to me i sighed and said the heck with it timid on tg'.replace(' ', '')

mapping = {}
for x in ascii_lowercase:
    mapping[x] = start[out.index(x)]

encoded = ''.join(mapping[i] for i in out)
print(encoded)
