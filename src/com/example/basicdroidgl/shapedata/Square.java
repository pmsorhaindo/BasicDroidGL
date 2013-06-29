package com.example.basicdroidgl.shapedata;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square {

	// Class variables
	
	// A memory buffer used to hold the pixels that make up points on the square.
	private FloatBuffer vertexBuffer;
	/* A memory buffer  used to specify which specific points in the vertex Buffer.
	   would be used and in which order to draw them in to produce our square */
	private ShortBuffer drawListBuffer;
	
	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;
	static float squareCoords[] = { -0.5f, 0.5f, 0.0f,  // top left
									-0.5f, -0.5f, 0.0f, // bottom left
									0.5f, -0.5f, 0.0f,  // top right
									0.5f, 0.5f, 0.0f }; // top right
	
	/*The order in which to draw the vertices in order to produce triangles.
	  each value refers to a set of three floats forming a vertex in the vertexBuffer*/
	private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
		
	
	public Square() {
		/* Determine the capacity of the ByteBuffer array
		   The amount of coordinate values multiplied by the size of a float */
		int capacity = squareCoords.length * 4; 
		
		// Initialise vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(capacity);
		// Keep the byte order to the native method of the device.
		bb.order(ByteOrder.nativeOrder());
		// assign the newly created byte buffer to the VertexBuffer
		vertexBuffer = bb.asFloatBuffer();
		
		// Assign our coordinate data for the squares to the vertexBuffer
		vertexBuffer.put(squareCoords);
		// Point to the start of the data the buffer holds.
		vertexBuffer.position(0);
		
		// Initialise another buffer in the same manner as above for the draw list data
		capacity = drawOrder.length * 2; // there are 2 bytes in a short.
		ByteBuffer dlb = ByteBuffer.allocateDirect(capacity);
		dlb.order(ByteOrder.nativeOrder());
		// assign the new draw list ByteBuffer to our specific ShortBuffer
		drawListBuffer = dlb.asShortBuffer();
		// add the data from the drawOrder array to the new Buffer.
		drawListBuffer.put(drawOrder);
		// Point to the start of the data the buffer holds.
		drawListBuffer.position(0);
	}
	
}
